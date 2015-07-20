/**
 * Copyright 2010-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.macula.extension.pdf;

import java.io.IOException;

import org.apache.commons.lang.exception.NestableRuntimeException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

/**
 * <p>
 * <b>ResourceLoader</b> is ResourceLoader utils.
 * </p>
 *
 * @since 2015年7月20日
 * @author wei_luo
 * @version $Id: codetemplates.xml 3814 2012-11-21 08:46:30Z wilson $
 */
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
