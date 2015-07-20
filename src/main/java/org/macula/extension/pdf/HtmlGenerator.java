package org.macula.extension.pdf;

import java.io.BufferedWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.NestableRuntimeException;
import org.macula.ApplicationContext;
import org.macula.base.data.handle.impl.CustomFreemarkerBeanWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HtmlGenerator {

	private static Logger logger = LoggerFactory.getLogger(HtmlGenerator.class);

	public String generate(String template, Object context) {
		BufferedWriter writer = null;
		String htmlContent = null;
		FreeMarkerConfigurer freemarkerConfigurer = null;
		try {
			try {
				freemarkerConfigurer = ApplicationContext.getBean(FreeMarkerConfigurer.class);
			} catch (NullPointerException ex) {
				freemarkerConfigurer = new FreeMarkerConfigurer();
				freemarkerConfigurer.setPreferFileSystemAccess(false);
				freemarkerConfigurer.setTemplateLoaderPaths("classpath:views/");
				Properties settings = PropertiesLoaderUtils.loadAllProperties("classpath:freemarker.properties");
				freemarkerConfigurer.setFreemarkerSettings(settings);
			}

			Configuration config = freemarkerConfigurer.createConfiguration();
			Template tp = config.getTemplate(template);
			tp.setObjectWrapper(CustomFreemarkerBeanWrapper.getWrapperInstance());
			StringWriter stringWriter = new StringWriter();
			writer = new BufferedWriter(stringWriter);
			tp.process(context, writer);
			htmlContent = stringWriter.toString();
			writer.flush();
		} catch (Exception ex) {
			logger.error("Generate html error.", ex);
			throw new NestableRuntimeException(ex);
		} finally {
			if (writer != null) {
				IOUtils.closeQuietly(writer);
			}
		}
		return htmlContent;
	}
}