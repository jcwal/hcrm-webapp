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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DynamicInsert
@DynamicUpdate
public class Patient extends AbstractAuditable<Long> {

	private static final long serialVersionUID = 1L;

	// 住院号
	private String hospitalNumber;
	private String userName;
	private String sex;
	private int age;

	private String telephone;
	private String mobile;
	private String address;

	// 病种
	private String disease;

	// 入院时间
	private Date admissionTime;
	// 出院时间
	private Date dischargeTime;
	// 出院情况
	private String dischargeType;
	// 管床医生
	@ManyToOne
	private Doctor bedDoctor;

	// 科室
	private String department;
	// 主治医生
	@ManyToOne
	private Doctor attendingDoctor;
	// 住院医生
	@ManyToOne
	private Doctor residentDoctor;

	// 出院诊断
	private String dischargeDiagnosis;
	// 病理诊断
	private String pathologicalDiagnosis;

	// 诊断及用药情况
	private String treatment;

	// 出院医嘱
	private String dischargeAdvice;

	// 下次回访时间
	private Date nextVisitTime;

	// 回访间隔
	private int visitGap;

	/**
	 * @return the hospitalNumber
	 */
	public String getHospitalNumber() {
		return this.hospitalNumber;
	}

	/**
	 * @param hospitalNumber
	 *            the hospitalNumber to set
	 */
	public void setHospitalNumber(String hospitalNumber) {
		this.hospitalNumber = hospitalNumber;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return this.sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return this.mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the disease
	 */
	public String getDisease() {
		return this.disease;
	}

	/**
	 * @param disease
	 *            the disease to set
	 */
	public void setDisease(String disease) {
		this.disease = disease;
	}

	/**
	 * @return the admissionTime
	 */
	public Date getAdmissionTime() {
		return this.admissionTime;
	}

	/**
	 * @param admissionTime
	 *            the admissionTime to set
	 */
	public void setAdmissionTime(Date admissionTime) {
		this.admissionTime = admissionTime;
	}

	/**
	 * @return the dischargeTime
	 */
	public Date getDischargeTime() {
		return this.dischargeTime;
	}

	/**
	 * @param dischargeTime
	 *            the dischargeTime to set
	 */
	public void setDischargeTime(Date dischargeTime) {
		this.dischargeTime = dischargeTime;
	}

	/**
	 * @return the dischargeType
	 */
	public String getDischargeType() {
		return this.dischargeType;
	}

	/**
	 * @param dischargeType
	 *            the dischargeType to set
	 */
	public void setDischargeType(String dischargeType) {
		this.dischargeType = dischargeType;
	}

	/**
	 * @return the bedDoctor
	 */
	@JsonIgnore
	public Doctor getBedDoctor() {
		return this.bedDoctor;
	}

	/**
	 * @param bedDoctor
	 *            the bedDoctor to set
	 */
	public void setBedDoctor(Doctor bedDoctor) {
		this.bedDoctor = bedDoctor;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return this.department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the attendingDoctor
	 */
	@JsonIgnore
	public Doctor getAttendingDoctor() {
		return this.attendingDoctor;
	}

	/**
	 * @param attendingDoctor
	 *            the attendingDoctor to set
	 */
	public void setAttendingDoctor(Doctor attendingDoctor) {
		this.attendingDoctor = attendingDoctor;
	}

	/**
	 * @return the residentDoctor
	 */
	@JsonIgnore
	public Doctor getResidentDoctor() {
		return this.residentDoctor;
	}

	/**
	 * @param residentDoctor
	 *            the residentDoctor to set
	 */
	public void setResidentDoctor(Doctor residentDoctor) {
		this.residentDoctor = residentDoctor;
	}

	/**
	 * @return the dischargeDiagnosis
	 */
	public String getDischargeDiagnosis() {
		return this.dischargeDiagnosis;
	}

	/**
	 * @param dischargeDiagnosis
	 *            the dischargeDiagnosis to set
	 */
	public void setDischargeDiagnosis(String dischargeDiagnosis) {
		this.dischargeDiagnosis = dischargeDiagnosis;
	}

	/**
	 * @return the pathologicalDiagnosis
	 */
	public String getPathologicalDiagnosis() {
		return this.pathologicalDiagnosis;
	}

	/**
	 * @param pathologicalDiagnosis
	 *            the pathologicalDiagnosis to set
	 */
	public void setPathologicalDiagnosis(String pathologicalDiagnosis) {
		this.pathologicalDiagnosis = pathologicalDiagnosis;
	}

	/**
	 * @return the treatment
	 */
	public String getTreatment() {
		return this.treatment;
	}

	/**
	 * @param treatment
	 *            the treatment to set
	 */
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	/**
	 * @return the dischargeAdvice
	 */
	public String getDischargeAdvice() {
		return this.dischargeAdvice;
	}

	/**
	 * @param dischargeAdvice
	 *            the dischargeAdvice to set
	 */
	public void setDischargeAdvice(String dischargeAdvice) {
		this.dischargeAdvice = dischargeAdvice;
	}

	public Date getNextVisitTime() {
		return nextVisitTime;
	}

	public void setNextVisitTime(Date nextVisitTime) {
		this.nextVisitTime = nextVisitTime;
	}

	public int getVisitGap() {
		return visitGap;
	}

	public void setVisitGap(int visitGap) {
		this.visitGap = visitGap;
	}

	// ////////////////////////////////JSON//////////////////////////////////////////////

	public Long getAttendingDoctorId() {
		return this.getAttendingDoctor() != null ? this.getAttendingDoctor().getId() : null;
	}

	public void setAttendingDoctorId(Long id) {
		this.setAttendingDoctor(id == null ? null : new Doctor(id));
	}

	public Long getResidentDoctorId() {
		return this.getResidentDoctor() != null ? this.getResidentDoctor().getId() : null;
	}

	public void setResidentDoctorId(Long id) {
		this.setResidentDoctor(id == null ? null : new Doctor(id));
	}

	public Long getBedDoctorId() {
		return this.getBedDoctor() != null ? this.getBedDoctor().getId() : null;
	}

	public void setBedDoctorId(Long id) {
		this.setBedDoctor(id == null ? null : new Doctor(id));
	}

	public int getResidentDays() {
		if (this.getAdmissionTime() == null || this.getDischargeTime() == null) {
			return 0;
		}
		return (int) ((this.getDischargeTime().getTime() - this.getAdmissionTime().getTime()) / (24 * 60 * 60 * 1000)) + 1;
	}
}
