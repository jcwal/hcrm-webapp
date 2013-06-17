/**
 * JcwalMenuAdminController.java 2011-3-17
 */
package org.jcwal.hospital.hcrm.controller;

import java.util.Collection;

import org.macula.base.security.domain.Menu;
import org.macula.base.security.domain.Resource;
import org.macula.base.security.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p> <b>JcwalMenuAdminController</b> 是菜单获取的Controller. </p>
 * 
 * @since 2011-3-17
 * @author Wilson Luo
 * @version $Id: JcwalMenuAdminController.java 3508 2012-08-22 10:24:12Z wilson $
 */
@Controller
public class JcwalMenuAdminController extends JcwalWebappAdminController {

	@ResponseBody
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public Collection<Resource> getMenuTree() {
		return SecurityUtils.getUserDetails().getResourcesTree(Menu.NAME, null, -1);
	}

	@ResponseBody
	@RequestMapping(value = "/menu/{root}/{level}", method = RequestMethod.GET)
	public Collection<Resource> getMenuTree(@PathVariable("root") Long root, @PathVariable("level") int level) {
		return SecurityUtils.getUserDetails().getResourcesTree(Menu.NAME, root, level);
	}
}
