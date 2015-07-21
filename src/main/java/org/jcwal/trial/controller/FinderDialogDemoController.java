 
package org.jcwal.trial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

 
@Controller
public class FinderDialogDemoController extends TrialAdminController {

	@RequestMapping("/finder-dialog/index")
	public String index() {
		return super.getRelativePath("/finder-dialog/index");
	}
}
