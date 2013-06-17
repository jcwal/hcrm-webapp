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
package org.jcwal.hospital.hcrm.repository;

import java.util.List;

import org.jcwal.hospital.hcrm.domain.Patient;
import org.macula.core.repository.MaculaJpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends MaculaJpaRepository<Patient, Long> {

	@Query("from Patient t where t.nextVisitTime is null or t.nextVisitTime < sysdate")
	List<Patient> getUnscheduledPatient();
}
