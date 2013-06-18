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
package org.jcwal.hospital.hcrm.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.macula.core.domain.AbstractAuditable;

@Entity
@DynamicInsert
@DynamicUpdate
public class ReturnVisit extends AbstractAuditable<Long> {

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false)
	private Patient patient;

	private Date planVisitDate;

	private Date actualVisitDate;

	@ManyToOne
	private Doctor visitDoctor;

	private String comment;

	private boolean complete;

	/**
	 * @return the planVisitDate
	 */
	public Date getPlanVisitDate() {
		return this.planVisitDate;
	}

	/**
	 * @param planVisitDate
	 *            the planVisitDate to set
	 */
	public void setPlanVisitDate(Date planVisitDate) {
		this.planVisitDate = planVisitDate;
	}

	/**
	 * @return the actualVisitDate
	 */
	public Date getActualVisitDate() {
		return this.actualVisitDate;
	}

	/**
	 * @param actualVisitDate
	 *            the actualVisitDate to set
	 */
	public void setActualVisitDate(Date actualVisitDate) {
		this.actualVisitDate = actualVisitDate;
	}

	/**
	 * @return the visitDoctor
	 */
	public Doctor getVisitDoctor() {
		return this.visitDoctor;
	}

	/**
	 * @param visitDoctor
	 *            the visitDoctor to set
	 */
	public void setVisitDoctor(Doctor visitDoctor) {
		this.visitDoctor = visitDoctor;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the complete
	 */
	public boolean isComplete() {
		return this.complete;
	}

	/**
	 * @param complete
	 *            the complete to set
	 */
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	// //////////////////////////////////JSON//////////////////////////////////////////////
	public Long getVisitDoctorId() {
		return this.getVisitDoctor() == null ? null : this.getVisitDoctor().getId();
	}

	public void setVisitDoctorId(Long id) {
		this.setVisitDoctor(id == null ? null : new Doctor(id));
	}
}
