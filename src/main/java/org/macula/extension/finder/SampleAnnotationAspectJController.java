package org.macula.extension.finder;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.macula.extension.aspectj.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleAnnotationAspectJController {

	@Autowired
	private SampleService sampleService;

	@RequestMapping("/admin/sampleAnnotaionAspectj")
	public void test(HttpServletResponse response) throws IOException {
		System.out.println("Call /admin/sampleAnnotaionAspectj");
		response.getWriter().print("Success: " + sampleService.getMyName());
		return;
	}
}
