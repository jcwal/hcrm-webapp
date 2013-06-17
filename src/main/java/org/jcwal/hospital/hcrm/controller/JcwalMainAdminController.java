/**
 * JcwalMainAdminController.java 2011-5-6
 */
package org.jcwal.hospital.hcrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p> <b>JcwalMainAdminController</b> 测试用的首页. </p>
 * 
 * @since 2011-5-6
 * @author Wilson Luo
 * @version $Id: JcwalMainAdminController.java 2350 2011-11-07 03:06:00Z wzp $
 */
@Controller
public class JcwalMainAdminController extends JcwalWebappAdminController {

	@RequestMapping(value = "/main" )
	public String index() {
		return super.getRelativePath("/admin/jcwal-hcrm-webapp/main");
	}
}
