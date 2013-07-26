package org.jcwal.hospital.hcrm.controller;

import javax.validation.Valid;

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
import org.springframework.web.servlet.View;

@Controller
public class VisitManagerController extends AdminHCRMBaseController {

	@Autowired
	private ReturnVisitRepository returnVisitRepository;
	@Autowired
	private PatientRepository patientRepository;

	@RequestMapping(value = "/returnvisit/list", method = RequestMethod.GET)
	public View index() {
		return new FinderView(HospitalSchemaRegistory.VISIT_SCHEMA);
	}

	@RequestMapping(value = "/returnvisit/edit", method = RequestMethod.POST)
	public String edit(@FormBean("selection") FinderSelection selection, Model model) {
		String returnvisitId = selection.getItems().get(0);
		model.addAttribute("returnvisitId", returnvisitId);
		return super.getRelativePath("/returnvisit/edit");
	}

	@RequestMapping(value = "/returnvisit/save", method = RequestMethod.POST)
	@OpenApi
	public Long save(@FormBean("returnvisit") @Valid ReturnVisit returnVisit) {
		if (hasErrors()) {
			throw new FormBindException(getMergedBindingResults());
		}
		returnVisitRepository.save(returnVisit);
		patientRepository.save(returnVisit.getPatient());
		return returnVisit.getId();
	}

	@RequestMapping(value = "/returnvisit/get/{returnvisitId}", method = RequestMethod.GET)
	@OpenApi
	public ReturnVisit getUserInfo(@PathVariable("returnvisitId") Long returnvisitId) {
		return returnVisitRepository.findOne(returnvisitId);
	}

	@RequestMapping(value = "/returnvisit/delete", method = RequestMethod.POST)
	@OpenApi
	public boolean delete(@FormBean("selection") FinderSelection selection) {
		for (String itemId : selection.getIterable(SecurityUtils.getUserContext(), true)) {
			if (itemId != null) {
				returnVisitRepository.delete(Long.parseLong(itemId));
			}
		}
		return true;
	}
}
