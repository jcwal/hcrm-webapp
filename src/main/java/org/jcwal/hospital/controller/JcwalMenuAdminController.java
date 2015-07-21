package org.jcwal.hospital.controller;

import java.util.Collection;

import org.macula.base.security.domain.Menu;
import org.macula.base.security.domain.Resource;
import org.macula.base.security.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
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
