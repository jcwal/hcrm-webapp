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

import java.util.Date;
import java.util.List;

import org.jcwal.hospital.hcrm.domain.Patient;
import org.jcwal.hospital.hcrm.domain.ReturnVisit;
import org.macula.core.repository.MaculaJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReturnVisitRepository extends MaculaJpaRepository<ReturnVisit, Long> {

	List<ReturnVisit> findByPatientOrderByPlanVisitDateAsc(Patient patient);

	@Query("from ReturnVisit t where t.patient = :patient and to_char(t.planVisitDate, 'YYYY-MM-DD') = to_char(:day, 'YYYY-MM-DD')")
	List<ReturnVisit> findByPatientPlanVistTime(@Param("patient") Patient patient, @Param("day") Date day);
}
