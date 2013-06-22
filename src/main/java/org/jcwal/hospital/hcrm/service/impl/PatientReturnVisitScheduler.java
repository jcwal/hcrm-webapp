package org.jcwal.hospital.hcrm.service.impl;

import java.util.Date;
import java.util.List;

import org.jcwal.hospital.hcrm.domain.Patient;
import org.jcwal.hospital.hcrm.domain.ReturnVisit;
import org.jcwal.hospital.hcrm.repository.PatientRepository;
import org.jcwal.hospital.hcrm.repository.ReturnVisitRepository;
import org.joda.time.DateTime;
import org.macula.core.utils.EnvironmentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PatientReturnVisitScheduler {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private ReturnVisitRepository returnVisitRepository;

	@Scheduled(cron = "0 0 0/1 * * ?")
	public void schedule() {
		log.info("Start check patient return visit.....");
		List<Patient> patients = patientRepository.getUnscheduledPatient();
		for (Patient patient : patients) {
			if (patient.getVisitGap() > 0) {
				Date visitTime = patient.getNextVisitTime();
				if (visitTime == null) {
					visitTime = patient.getDischargeTime();
				}
				if (visitTime == null) {
					visitTime = EnvironmentUtils.getCurrentTime();
				}
				DateTime theVisitTime = new DateTime(visitTime);
				while (theVisitTime.isBefore(EnvironmentUtils.getCurrentTime().getTime())) {
					theVisitTime = theVisitTime.plusDays(patient.getVisitGap());
				}
				visitTime = theVisitTime.toDate();
				List<ReturnVisit> returnVisit = returnVisitRepository.findByPatientPlanVistTime(patient, visitTime);
				if (returnVisit.isEmpty()) {
					ReturnVisit visit = new ReturnVisit();
					visit.setPatient(patient);
					visit.setPlanVisitDate(visitTime);
					returnVisitRepository.save(visit);
				}
				patient.setNextVisitTime(visitTime);
				patientRepository.save(patient);
			}
		}

	}
}
