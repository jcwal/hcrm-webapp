 
package org.macula.extension.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

 
@Aspect
@Component
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
