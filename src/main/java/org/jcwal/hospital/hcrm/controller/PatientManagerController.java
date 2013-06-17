package org.jcwal.hospital.hcrm.controller;

import java.util.List;

import javax.validation.Valid;

import org.jcwal.hospital.hcrm.domain.Patient;
import org.jcwal.hospital.hcrm.domain.ReturnVisit;
import org.jcwal.hospital.hcrm.repository.PatientRepository;
import org.jcwal.hospital.hcrm.repository.ReturnVisitRepository;
import org.jcwal.hospital.hcrm.service.impl.HospitalSchemaRegistory;
import org.macula.base.security.util.SecurityUtils;
import org.macula.core.exception.FormBindException;
import org.macula.core.mvc.annotation.FormBean;
import org.macula.core.mvc.annotation.OpenApi;
import org.macula.plugins.mda.finder.view.FinderView;
import org.macula.plugins.mda.finder.vo.FinderSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

@Controller
public class PatientManagerController extends AdminHCRMBaseController {

	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private ReturnVisitRepository returnVisitRepository;

	@RequestMapping(value = "/patient/list", method = RequestMethod.GET)
	public View index() {
		return new FinderView(HospitalSchemaRegistory.PATIENT_SCHEMA);
	}

	@RequestMapping(value = "/patient/create", method = RequestMethod.POST)
	public String create(Model model) {
		return super.getRelativePath("/patient/edit");
	}

	@RequestMapping(value = "/patient/delete", method = RequestMethod.POST)
	@OpenApi
	public boolean delete(@FormBean("selection") FinderSelection selection) {
		for (String itemId : selection.getIterable(SecurityUtils.getUserContext(), true)) {
			if (itemId != null) {
				patientRepository.delete(Long.parseLong(itemId));
			}
		}
		return true;
	}

	@RequestMapping(value = "/patient/edit", method = RequestMethod.POST)
	public String edit(@FormBean("selection") FinderSelection selection, Model model) {
		String patientId = selection.getItems().get(0);
		model.addAttribute("patientId", patientId);
		return super.getRelativePath("/patient/edit");
	}

	@RequestMapping(value = "/patient/save", method = RequestMethod.POST)
	@OpenApi
	public Long save(@FormBean("patient") @Valid Patient patient) {
		if (hasErrors()) {
			throw new FormBindException(getMergedBindingResults());
		}
		patientRepository.save(patient);
		return patient.getId();
	}

	@RequestMapping(value = "/patient/get/{patientId}", method = RequestMethod.GET)
	@OpenApi
	public Patient getUserInfo(@PathVariable("patientId") Long patientId) {
		return patientRepository.findOne(patientId);
	}

	@RequestMapping(value = "/patient/detail", method = RequestMethod.GET)
	public String viewsend(Model model, @RequestParam("item") Long id) {
		Patient patient = patientRepository.findOne(id);
		List<ReturnVisit> returnvisits = returnVisitRepository.findByPatientOrderByPlanVisitDateAsc(patient);
		model.addAttribute("patient", patient);
		model.addAttribute("visits", returnvisits);
		return super.getRelativePath("/patient/detail");
	}

}
