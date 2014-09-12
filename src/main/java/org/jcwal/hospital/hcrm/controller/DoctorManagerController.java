package org.jcwal.hospital.hcrm.controller;

import javax.validation.Valid;

import org.jcwal.hospital.hcrm.domain.Doctor;
import org.jcwal.hospital.hcrm.exception.HCRMException;
import org.jcwal.hospital.hcrm.repository.DoctorRepository;
import org.jcwal.hospital.hcrm.service.impl.HospitalSchemaRegistory;
import org.macula.base.security.util.SecurityUtils;
import org.macula.core.exception.FormBindException;
import org.macula.core.mvc.annotation.FormBean;
import org.macula.core.mvc.annotation.OpenApi;
import org.macula.plugins.mda.finder.view.FinderView;
import org.macula.plugins.mda.finder.vo.FinderSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

@Controller
public class DoctorManagerController extends AdminHCRMBaseController {

	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private Md5PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/doctor/list", method = RequestMethod.GET)
	public View index() {
		return new FinderView(HospitalSchemaRegistory.DOCTOR_SCHEMA);
	}

	@RequestMapping(value = "/doctor/create", method = RequestMethod.POST)
	public String create(Model model) {
		return super.getRelativePath("/doctor/edit");
	}

	@RequestMapping(value = "/doctor/delete", method = RequestMethod.POST)
	@OpenApi
	public boolean delete(@FormBean("selection") FinderSelection selection) {
		for (String itemId : selection.getIterable(SecurityUtils.getUserContext(), true)) {
			if (itemId != null) {
				doctorRepository.delete(Long.parseLong(itemId));
			}
		}
		return true;
	}

	@RequestMapping(value = "/doctor/edit", method = RequestMethod.POST)
	public String edit(@FormBean("selection") FinderSelection selection, Model model) {
		String doctorId = selection.getItems().get(0);
		model.addAttribute("doctorId", doctorId);
		return super.getRelativePath("/doctor/edit");
	}

	@RequestMapping(value = "/doctor/save", method = RequestMethod.POST)
	@OpenApi
	public Long save(@FormBean("doctor") @Valid Doctor doctor) {
		if (hasErrors()) {
			throw new FormBindException(getMergedBindingResults());
		}
		if (doctor.isNew()) {
			doctor.setPassword(passwordEncoder.encodePassword(doctor.getPassword(), null));
		}
		doctorRepository.save(doctor);
		return doctor.getId();
	}

	@RequestMapping(value = "/doctor/get/{doctorId}", method = RequestMethod.GET)
	@OpenApi
	public Doctor getUserInfo(@PathVariable("doctorId") Long doctorId) {
		return doctorRepository.findOne(doctorId);
	}

	@RequestMapping(value = "/doctor/changepassword", method = RequestMethod.POST)
	public String adminChangePassword(@FormBean("selection") FinderSelection selection, Model model) {
		String doctorId = selection.getItems().get(0);
		Doctor doctor = doctorRepository.findOne(Long.parseLong(doctorId));
		model.addAttribute("doctor", doctor);
		model.addAttribute("isAdmin", true);
		return super.getRelativePath("/doctor/changepassword");
	}

	@RequestMapping(value = "/doctor/changepassword", method = RequestMethod.GET)
	public String adminChangePassword(Model model) {
		String username = SecurityUtils.getUserDetails().getUsername();
		Doctor doctor = doctorRepository.findByUsername(username);
		model.addAttribute("doctor", doctor);
		return super.getRelativePath("/doctor/changepassword");
	}

	@RequestMapping(value = "/doctor/adminsavepassword", method = RequestMethod.POST)
	@OpenApi
	public Long adminSavePassword(@RequestParam("doctorId") String doctorId,
			@RequestParam(value = "newpassword", required = true) String newPassword) {
		Doctor doctor = doctorRepository.findOne(Long.parseLong(doctorId));
		if (doctor != null) {
			doctor.setPassword(passwordEncoder.encodePassword(newPassword, null));
			doctorRepository.save(doctor);
			return doctor.getId();
		}
		return null;
	}

	@RequestMapping(value = "/doctor/savepassword", method = RequestMethod.POST)
	@OpenApi
	public Long savePassword(@RequestParam("doctorId") String doctorId,
			@RequestParam(value = "oldpassword", required = true) String oldPassword,
			@RequestParam(value = "newpassword", required = true) String newPassword) {
		Doctor doctor = doctorRepository.findOne(Long.parseLong(doctorId));
		if (doctor == null || !doctor.getUsername().equals(SecurityUtils.getUserDetails().getUsername())) {
			throw new HCRMException("权限不足！");
		}
		if (!doctor.getPassword().equals(passwordEncoder.encodePassword(oldPassword, null))) {
			throw new HCRMException("旧密码输入错误！");
		}
		doctor.setPassword(passwordEncoder.encodePassword(newPassword, null));
		doctorRepository.save(doctor);
		return doctor.getId();
	}
}
