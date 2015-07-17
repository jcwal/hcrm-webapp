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
package org.macula.extension.aspectj;

/**
 * <p>
 * <b>SampleService</b> is SampleService.
 * </p>
 *
 * @since 2015年7月17日
 * @author jokeway
 * @version $Id: codetemplates.xml 3814 2012-11-21 08:46:30Z wilson $
 */
public interface SampleService {

	@SampleAnnotation
	public String getMyName();
}
