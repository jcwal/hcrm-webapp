package org.jcwal.hospital.hcrm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jcwal.hospital.hcrm.domain.Patient;
import org.jcwal.hospital.hcrm.repository.PatientRepository;
import org.jcwal.hospital.hcrm.service.impl.HospitalSchemaRegistory;
import org.macula.core.utils.DateFormatUtils;
import org.macula.plugins.mda.finder.view.FinderView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

@Controller
public class WorkloadManagerController extends AdminHCRMBaseController {

	@Autowired
	private PatientRepository patientRepository;

	@RequestMapping(value = "/workload/list", method = RequestMethod.GET)
	public View index() {
		return new FinderView(HospitalSchemaRegistory.WORKLOAD_SCHEMA);
	}

	@RequestMapping(value = "/workload/detail", method = RequestMethod.GET)
	public String viewsend(Model model, @RequestParam("item") String pk) {
		List<Patient> patients = null;
		try {
			if (!StringUtils.equalsIgnoreCase("null", pk)) {
				String[] items = pk.split("-");
				String doctorId = items[0];
				Date maxDay = DateFormatUtils.parse(items[1], "yyyyMMdd");
				Date minDay = DateFormatUtils.parse(items[2], "yyyyMMdd");
				patients = patientRepository.findDoctorPatients(Long.parseLong(doctorId), maxDay, minDay);
			}
		} catch (Exception ex) {
			//ignore
		}
		model.addAttribute("patients", patients == null ? new ArrayList<Patient>() : patients);
		return super.getRelativePath("/workload/detail");
	}
}
