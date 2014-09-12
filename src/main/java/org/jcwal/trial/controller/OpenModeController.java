/**
 * OpenModeController.java 2011-9-30
 */
package org.jcwal.trial.controller;

import org.macula.base.data.vo.CommonCondition;
import org.macula.core.mvc.annotation.OpenApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p> <b>OpenModeController</b> 是打开方式示例. </p>
 * 
 * @since 2011-9-30
 * @author Wilson Luo
 * @version $Id: OpenModeController.java 2015 2011-09-30 03:35:16Z wilson $
 */
@Controller
public class OpenModeController extends TrialAdminController {

	@RequestMapping("/openmode/index")
	public String index() {
		return super.getRelativePath("/openmode/index");
	}

	@RequestMapping("/openmode/update")
	public String updateContent() {
		return super.getRelativePath("/openmode/update");
	}

	@RequestMapping("/openmode/replace")
	public String replaceContent() {
		return super.getRelativePath("/openmode/replace");
	}

	@RequestMapping("/openmode/dialog")
	public String dialogContent() {
		return super.getRelativePath("/openmode/dialog");
	}

	@RequestMapping("/openmode/blank")
	public String blankContent() {
		return super.getRelativePath("/openmode/blank");
	}

	@RequestMapping("/openmode/onCommand")
	@OpenApi
	public CommonCondition commandContent() {
		return new CommonCondition();
	}

}
