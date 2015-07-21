 
package org.jcwal.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

 
@Controller
public class JcwalMainAdminController extends JcwalWebappAdminController {

	@RequestMapping(value = "/main" )
	public String index() {
		return super.getRelativePath("/admin/jcwal-hcrm-webapp/main");
	}
}
