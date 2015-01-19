package org.jcwal.trial.controller;

import javax.validation.Valid;

import org.jcwal.hospital.hcrm.domain.Doctor;
import org.jcwal.hospital.hcrm.repository.DoctorRepository;
import org.macula.core.exception.FormBindException;
import org.macula.core.mvc.annotation.FormBean;
import org.macula.core.mvc.annotation.OpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoctorManagerController2 extends TrialAdminController {

	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private Md5PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/doctor/list", method = RequestMethod.GET)
	public String index() {
		return super.getRelativePath("/doctor/list");
	}

	@RequestMapping(value = "/doctor/listDatas", method = RequestMethod.GET)
	@OpenApi
	public Page<Doctor> indexDatas(Pageable pageable) {
		return doctorRepository.findAll(pageable);
	}

	@RequestMapping(value = "/doctor/create", method = RequestMethod.GET)
	public String create(Model model) {
		return super.getRelativePath("/doctor/edit");
	}

	@RequestMapping(value = "/doctor/delete/{doctorId}", method = RequestMethod.POST)
	@OpenApi
	public boolean delete(@PathVariable("doctorId") String itemId) {
		doctorRepository.delete(Long.parseLong(itemId));
		return true;
	}

	@RequestMapping(value = "/doctor/edit/{doctorId}", method = RequestMethod.GET)
	public String edit(@PathVariable("doctorId") String doctorId, Model model) {
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

}
