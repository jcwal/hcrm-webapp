package org.macula.extension.pdf;

import java.io.IOException;

import org.apache.commons.lang.exception.NestableRuntimeException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;


public class ResourceLoader {

	private static final org.springframework.core.io.ResourceLoader loader = new DefaultResourceLoader();

	public static String getPath(String path) {
		Resource resource = loader.getResource(path);
		try {
			return resource.getURL().getPath();
		} catch (IOException e) {
			throw new NestableRuntimeException(e);
		}
	}

}
