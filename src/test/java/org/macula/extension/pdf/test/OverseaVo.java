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
package org.macula.extension.pdf.test;

/**
 * <p>
 * <b>OverseaVo</b> is
 * </p>
 *
 * @since 2015年7月20日
 * @author wei_luo
 * @version $Id: codetemplates.xml 3814 2012-11-21 08:46:30Z wilson $
 */
public class OverseaVo {

	/**
	 * 保单号
	 */
	private String policyNo;
	/**
	 * 投保人姓名
	 */
	private String holderName;
	/**
	 * 投被保人关系
	 */
	private String relation;
	/**
	 * 投保人联络地址
	 */
	private String holderAdress;
	/**
	 * 投保人邮编
	 */
	private String holderPostCode;

	/**
	 * 被保险人姓名
	 */
	private String insuredName;
	/**
	 * 被保险人姓名拼音
	 */
	private String insuredPingyinName;
	/**
	 * 被保险人护照号码
	 */
	private String insuredPassportNo;
	/**
	 * 被保险人性别
	 */
	private String insuredSex;
	/**
	 * 被保险人出生日期
	 */
	private String insuredBirthday;
	/**
	 * 被保险人电话
	 */
	private String insuredPhone;

	/**
	 * 被保险人证件号码
	 */
	private String insuredIDNo;

	/**
	 * 前往国家或地区
	 */
	private String destination;

	/**
	 * 受益人姓名
	 */
	private String beneficiaryName;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 保险期间
	 */
	private String period;

	/**
	 * 境外意外伤害或残疾保额
	 */
	private String accidentalSumInsured;

	/**
	 * 紧急救援医疗保额
	 */
	private String emergencySumInsured;

	/**
	 * 附加境外紧急救援医保额
	 */
	private String medicalSumInsured;

	/**
	 * 总保费
	 */
	private String premium;
	/**
	 * 签发日期
	 */
	private String issueDate;

	/**
	 * 省份
	 */
	private String branchName;

	/**
	 * 合作公司名称
	 */
	private String companyName;

	/**
	 * 图片存放路径
	 */
	private String imagePath;

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getHolderAdress() {
		return holderAdress;
	}

	public void setHolderAdress(String holderAdress) {
		this.holderAdress = holderAdress;
	}

	public String getHolderPostCode() {
		return holderPostCode;
	}

	public void setHolderPostCode(String holderPostCode) {
		this.holderPostCode = holderPostCode;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getInsuredPingyinName() {
		return insuredPingyinName;
	}

	public void setInsuredPingyinName(String insuredPingyinName) {
		this.insuredPingyinName = insuredPingyinName;
	}

	public String getInsuredPassportNo() {
		return insuredPassportNo;
	}

	public void setInsuredPassportNo(String insuredPassportNo) {
		this.insuredPassportNo = insuredPassportNo;
	}

	public String getInsuredSex() {
		return insuredSex;
	}

	public void setInsuredSex(String insuredSex) {
		this.insuredSex = insuredSex;
	}

	public String getInsuredBirthday() {
		return insuredBirthday;
	}

	public void setInsuredBirthday(String insuredBirthday) {
		this.insuredBirthday = insuredBirthday;
	}

	public String getInsuredPhone() {
		return insuredPhone;
	}

	public void setInsuredPhone(String insuredPhone) {
		this.insuredPhone = insuredPhone;
	}

	public String getInsuredIDNo() {
		return insuredIDNo;
	}

	public void setInsuredIDNo(String insuredIDNo) {
		this.insuredIDNo = insuredIDNo;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAccidentalSumInsured() {
		return accidentalSumInsured;
	}

	public void setAccidentalSumInsured(String accidentalSumInsured) {
		this.accidentalSumInsured = accidentalSumInsured;
	}

	public String getEmergencySumInsured() {
		return emergencySumInsured;
	}

	public void setEmergencySumInsured(String emergencySumInsured) {
		this.emergencySumInsured = emergencySumInsured;
	}

	public String getMedicalSumInsured() {
		return medicalSumInsured;
	}

	public void setMedicalSumInsured(String medicalSumInsured) {
		this.medicalSumInsured = medicalSumInsured;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String findPrimaryKey() {
		return this.policyNo;
	}

}