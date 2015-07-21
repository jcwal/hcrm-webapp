package org.jcwal.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 
@Controller
public class JcwalAjaxForwardAdminController extends JcwalWebappAdminController {

	@RequestMapping(value = "/ajaxforward", method = RequestMethod.GET)
	public String forward() {
		return super.getRelativePath("/ajaxforward");
	}
	
	@RequestMapping(value = "/close", method = RequestMethod.GET)
	public String close() {
		return super.getRelativePath("/close");
	}
}
