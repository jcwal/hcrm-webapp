package org.macula.extension.upload.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.macula.core.utils.DateFormatUtils;
import org.macula.extension.upload.UploadEnhanceOpenApiTemplate;
import org.macula.plugins.esb.openapi.vo.ExecuteResponse;

import com.fasterxml.jackson.core.type.TypeReference;

public class UploadFileTest extends TestCase {

	private final static String UPLOAD_FILE_URL = "/gbss-esb/admin/gbss-esb-dealer/qualityReport/upload";

	@Test
	public void testUpload() throws IOException {

		UploadEnhanceOpenApiTemplate template = new UploadEnhanceOpenApiTemplate();

		template.setAppId("gbss-trade");
		template.setAppSecret("gbss-trade");
		template.setEndpoint("https://gbssdev.infinitus.com.cn");

		String filePath = "d:\\temp\\key.txt";
		File file = new File(filePath);

		Map<String, Object> postValues = new HashMap<String, Object>();
		postValues.put("opAppNo", "123");
		postValues.put("certDataType", "P");
		postValues.put("allowDownload", "true");
		postValues.put("effectiveDate", "2015-07-07");
		postValues.put("inactiveDate", "2015-08-07");
		postValues.put("uploadUserName", "220029838");
		postValues.put("uploadDateTime", "2015-07-07");
		postValues.put("comments", "Comments");

		Map<String, File> files = new HashMap<String, File>();
		files.put("byteFile", file);

		ExecuteResponse<Map<String, Object>> result = template.postForObject(UPLOAD_FILE_URL, null, null,
				new TypeReference<ExecuteResponse<Map<String, Object>>>() {
				}, postValues, files);

		System.out.println("Success: " + result.isSuccess());

		Map<String, Object> returnObject = result.getReturnObject();
		System.out.println("Result: " + returnObject);
		if (MapUtils.isEmpty(returnObject)) {
			System.out.println("Upload completed success.");
		} else {
			for (Map.Entry<String, Object> entry : returnObject.entrySet()) {
				System.out.println("Upload error: " + entry.getKey() + "->" + entry.getValue());
			}
		}

	}
}
