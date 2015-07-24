package org.macula.extension.upload.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;
import org.macula.extension.upload.UploadEnchanceOpenApiTemplate;

import com.fasterxml.jackson.core.type.TypeReference;

public class UploadFileTest extends TestCase {

	@Test
	public void testUpload() {

		UploadEnchanceOpenApiTemplate template = new UploadEnchanceOpenApiTemplate();

		template.setAppId("app-id");
		template.setAppSecret("app-secret");
		template.setEndpoint("http://localhost/hcrm-webapp");

		Map<String, Object> postValues = new HashMap<String, Object>();
		postValues.put("document.uploadUserName", "admin123");
		postValues.put("document.effectiveDate", "admin123");

		Map<String, File> files = new HashMap<String, File>();
		files.put("documentFile", new File("d:\\temp\\key.txt"));

		template.postForObject("/admin/uploadDocument/save", null, null, new TypeReference<Object>() {
		}, postValues, files);

	}
}
