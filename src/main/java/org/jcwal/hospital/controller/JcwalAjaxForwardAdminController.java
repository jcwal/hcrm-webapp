/**
 * JcwalAjaxForwardAdminController.java 2011-6-10
 */
package org.jcwal.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p> <b>JcwalAjaxForwardAdminController</b> 是登录成功后的Forward页面，针对于原始请求为Ajax的情况. </p>
 * 
 * @since 2011-6-10
 * @author Wilson Luo
 * @version $Id: JcwalAjaxForwardAdminController.java 2746 2011-12-07 04:42:28Z wzp $
 */
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
