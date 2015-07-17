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

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * <p>
 * <b>SampleAspectjInterceptor</b> is AspectJ Interceptor sample.
 * </p>
 *
 * @since 2015年7月17日
 * @author jokeway
 * @version $Id: codetemplates.xml 3814 2012-11-21 08:46:30Z wilson $
 */
@Aspect
public class SampleAspectjInterceptor {

	@Pointcut("@annotation(sampleAnnotation)")
	public void onSampleAnnotationMethod(SampleAnnotation sampleAnnotation) {
	}

	@Before("onSampleAnnotationMethod(sampleAnnotation)")
	public void beforeSampleAnnotationMethod(JoinPoint joinPoint, SampleAnnotation sampleAnnotation) {
		System.out.println("============ beforeSampleAnnotationMethod ============");
	}

	@AfterReturning(pointcut = "onSampleAnnotationMethod(sampleAnnotation)", returning = "returnValue")
	public void afterSampleAnnotationMethod(JoinPoint joinPoint, SampleAnnotation sampleAnnotation, Object returnValue) {
		System.out.println("============ afterSampleAnnotationMethod ============");
	}

	@AfterThrowing(pointcut = "onSampleAnnotationMethod(sampleAnnotation)", throwing = "exception")
	public void exceptionSampleAnnotationMethod(JoinPoint joinPoint, SampleAnnotation sampleAnnotation, Throwable exception) {
		System.out.println("============ exceptionSampleAnnotationMethod ============");
	}

}
