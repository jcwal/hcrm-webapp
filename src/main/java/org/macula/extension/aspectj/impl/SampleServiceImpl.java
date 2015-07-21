 
package org.macula.extension.aspectj.impl;

import org.macula.extension.aspectj.SampleAnnotation;
import org.macula.extension.aspectj.SampleService;
import org.springframework.stereotype.Service;
 
@Service
public class SampleServiceImpl implements SampleService {

	@SampleAnnotation
	public String getMyName() {
		return "Joke way";
	}
}
