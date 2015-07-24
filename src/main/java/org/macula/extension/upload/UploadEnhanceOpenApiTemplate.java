package org.macula.extension.upload;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.macula.core.mvc.json.ObjectMapperImpl;
import org.macula.core.mvc.xml.XmlMapperImpl;
import org.macula.core.utils.HttpRequestUtils;
import org.macula.core.utils.StringUtils;
import org.macula.plugins.esb.openapi.OpenApiException;
import org.macula.plugins.esb.openapi.OpenApiHelper;
import org.macula.plugins.esb.openapi.OpenApiTemplate;
import org.macula.plugins.esb.openapi.vo.ExecuteResponse;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UploadEnhanceOpenApiTemplate extends OpenApiTemplate {

	public UploadEnhanceOpenApiTemplate() {

	}

	public UploadEnhanceOpenApiTemplate(String endpoint, String appId, String appSecret) {
		this(endpoint, appId, appSecret, OpenApiHelper.FORMAT_JSON, OpenApiHelper.SIGN_METHOD_MD5, "zh_CN");
	}

	public UploadEnhanceOpenApiTemplate(String endpoint, String appId, String appSecret, String format,
			String signMethod, String locale) {
		super(endpoint, appId, appSecret, format, signMethod, locale);
	}

	@SuppressWarnings("unchecked")
	public <T> ExecuteResponse<T> postForObject(String path, String version, String sessionKey,
			TypeReference<?> responseType, Map<String, Object> postValues, Map<String, File> files,
			Object... urlVariables) {

		if (MapUtils.isEmpty(files)) {
			return postForObject(path, version, sessionKey, responseType, postValues, urlVariables);
		}

		ResponseEntity<String> response = postOrPutForResponseEntity(path, version, sessionKey, postValues,
				HttpMethod.POST, files, urlVariables);
		try {
			ExecuteResponse<T> obj = null;
			if (responseType != null) {
				if (OpenApiHelper.FORMAT_XML.equals(super.getFormat())) {
					// XML反序列化结果
					ObjectMapper mapper = new XmlMapperImpl();
					obj = mapper.readValue(response.getBody(), responseType);
				} else {
					// JSON反序列化结果
					ObjectMapperImpl mapper = new ObjectMapperImpl();
					obj = mapper.readValue(response.getBody(), responseType);
				}
			} else {
				obj = new ExecuteResponse<T>();
				obj.setReturnObject((T) response.getBody());
			}
			return obj;
		} catch (Exception e) {
			if (e instanceof RestClientException) {
				StringUtils.logRestClientException(log, (RestClientException) e);
			}
			throw new OpenApiException("error.openapi.response.marsharp", e);
		}
	}

	private ResponseEntity<String> postOrPutForResponseEntity(String path, String version, String sessionKey,
			Map<String, Object> postValues, HttpMethod post, Map<String, File> files, Object[] urlVariables) {
		try {

			// HEADER
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set(HttpRequestUtils.AJAX_REQUEST_HEADER, HttpRequestUtils.API_REQUEST_VALUE);

			// POST BODY(Parameter)
			MultiValueMap<String, String> parameterPostBody = new LinkedMultiValueMap<String, String>();
			if (null != postValues) {
				for (String key : postValues.keySet()) {
					MultiValueMap<String, String> namevalues = OpenApiHelper.getPostParamsMap(key, postValues.get(key));
					parameterPostBody.putAll(namevalues);
				}
			}

			// Open API协议
			String sessionUser = OpenApiHelper.getSessionUser(sessionKey);
			String queryString = OpenApiHelper.getOpenApiParamsMap(super.getAppId(), super.getAppSecret(), sessionKey,
					sessionUser, version, super.getFormat(), super.getSignMethod(), super.getLocale(),
					new LinkedMultiValueMap<String, String>());

			// POST BODY(Parameter)
			MultiValueMap<String, Object> realPostBody = new LinkedMultiValueMap<String, Object>();
			for (Map.Entry<String, List<String>> entry : parameterPostBody.entrySet()) {
				realPostBody.add(entry.getKey(), entry.getValue().get(0));
			}

			for (Map.Entry<String, File> entry : files.entrySet()) {
				String paramName = entry.getKey();
				File entryFile = entry.getValue();
				FileSystemResource resource = new FileSystemResource(entryFile);
				realPostBody.add(paramName, resource);
			}

			// ENTITY
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(
					realPostBody, requestHeaders);

			// URL
			UriComponents uriComponents = null;
			if (path.toLowerCase().startsWith("http:") || path.startsWith("https://")) {
				uriComponents = UriComponentsBuilder.fromUriString(path).query(queryString).build();
			} else {
				uriComponents = UriComponentsBuilder.fromUriString(getEndpoint() + path).query(queryString).build();
			}
			if (urlVariables != null) {
				uriComponents = uriComponents.expand(urlVariables);
			}
			uriComponents = uriComponents.encode();

			ResponseEntity<String> response = getRestTemplate().exchange(uriComponents.toUri(), post, requestEntity,
					String.class);

			HttpStatus status = response.getStatusCode();

			if (status == HttpStatus.OK || status == HttpStatus.CREATED || status == HttpStatus.ACCEPTED
					|| status == HttpStatus.INTERNAL_SERVER_ERROR) {
				return response;
			} else {
				throw new OpenApiException("error.openapi.response.status", new Object[] { status });
			}
		} catch (Exception ex) {
			if (ex instanceof RestClientException) {
				StringUtils.logRestClientException(log, (RestClientException) ex);
			}
			if (ex instanceof OpenApiException) {
				throw (OpenApiException) ex;
			}
			throw new OpenApiException("error.openapi.request.execute", ex);
		}
	}
}
