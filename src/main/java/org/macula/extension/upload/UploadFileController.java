package org.macula.extension.upload;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.macula.core.mvc.annotation.FormBean;
import org.macula.core.mvc.annotation.OpenApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadFileController {

	@RequestMapping("/admin/uploadDocument/index")
	public String uploadFilePage() {
		return "/admin/macula-trial/upload/index";
	}

	@OpenApi
	@RequestMapping("/admin/uploadDocument/save")
	public boolean saveUploadDocument(
			@RequestParam(value = "documentFile", required = false) MultipartFile documentFile,
			@FormBean("document") FileInfo document, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		Enumeration<?> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			System.out.println("RequestParameter names: " + names.nextElement());
		}
		System.out.println("Success call saveUploadDocument....");
		if (documentFile != null) {
			System.out.println("MultipartFile: " + documentFile.getOriginalFilename());
		} else {
			System.out.println("MultipartFile is null.");
		}
		if (document != null) {
			System.out.println("Document : " + document.getUploadUserName() + " - " + document.getEffectiveDate());
		} else {
			System.out.println("Document is null.");
		}
		return true;
	}
}
