package org.jcwal.hospital.hcrm.service.impl;

import javax.annotation.PostConstruct;

import org.macula.Configuration;
import org.macula.base.data.vo.CriteriaType;
import org.macula.base.data.vo.DataType;
import org.macula.base.data.vo.FieldControl;
import org.macula.plugins.mda.finder.domain.FinderAction;
import org.macula.plugins.mda.finder.domain.FinderColumn;
import org.macula.plugins.mda.finder.domain.FinderDataSet;
import org.macula.plugins.mda.finder.domain.FinderDetailView;
import org.macula.plugins.mda.finder.domain.FinderParam;
import org.macula.plugins.mda.finder.domain.FinderSchema;
import org.macula.plugins.mda.finder.domain.FinderTabView;
import org.macula.plugins.mda.finder.service.FinderSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HospitalSchemaRegistory {

	public static final String DOCTOR_SCHEMA = "DOCTOR_SCHEMA";
	public static final String PATIENT_SCHEMA = "PATIENT_SCHEMA";
	public static final String VISIT_SCHEMA = "VISIT_SCHEMA";

	@Autowired
	private FinderSchemaService memorySchemaService;

	@PostConstruct
	public void registry() {
		memorySchemaService.add(createPatientSchema());
		memorySchemaService.add(createDoctorSchema());
		memorySchemaService.add(createReturnVisitSchema());
	}

	private FinderSchema createDoctorSchema() {
		FinderSchema schema = new FinderSchema();
		schema.setCode(DOCTOR_SCHEMA);
		schema.setTitle("医生管理");
		schema.setRelativePath("admin/hcrm/doctor/list");

		FinderDataSet finderDataSet = new FinderDataSet("HCRM_DOCTOR_SET");
		schema.setFinderDataSet(finderDataSet);

		FinderParam param = null;
		FinderColumn column = new FinderColumn();

		column.setLabel("编号");
		column.setColumn("id");
		column.setName("id");
		column.setType(DataType.Long);
		column.setPkey(true);
		column.setVisible(false);
		schema.addColumn(column);

		column = new FinderColumn();
		column.setLabel("帐号");
		column.setColumn("USERNAME");
		column.setName("USERNAME");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("姓名");
		column.setColumn("NICKNAME");
		column.setName("NICKNAME");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("岗位");
		column.setColumn("TITLE");
		column.setName("TITLE");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("电话");
		column.setColumn("TELEPHONE");
		column.setName("TELEPHONE");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("手机");
		column.setColumn("MOBILE");
		column.setName("MOBILE");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		schema.setEnableFilter(true);
		schema.setEnableSearch(true);

		FinderAction action = new FinderAction();
		action.setLabel("新增医生");
		action.setHref("admin/hcrm/doctor/create");
		action.setTarget("dialog::{title: '新增医生', width:'720',height:'280'}");
		schema.addAction(action);

		action = new FinderAction();
		action.setLabel("编辑医生");
		action.setHref("admin/hcrm/doctor/edit");
		action.setTarget("dialog::{title: '编辑医生', width:'720',height:'250'}");
		action.setMaxRowSelected(1);
		action.setMinRowSelected(1);
		schema.addAction(action);

		action = new FinderAction();
		action.setLabel("删除医生");
		action.setHref("admin/hcrm/doctor/delete");
		action.setConfirm("您确定删除该医生吗？");
		action.setTarget("command");
		action.setMinRowSelected(1);
		schema.addAction(action);

		action = new FinderAction();
		action.setLabel("修改密码");
		action.setHref("admin/hcrm/doctor/changepassword");
		action.setTarget("dialog::{title: '修改密码', width:'580',height:'280'}");
		action.setMaxRowSelected(1);
		action.setMinRowSelected(1);
		schema.addAction(action);

		action = new FinderAction();
		action.setLabel("角色授权");
		action.setMinRowSelected(1);
		action.setMaxRowSelected(1);
		action.setTarget("dialog::{title:'角色授权',width:'800',height:'600',type:'POST'}");
		action.setHref("admin/macula-base/sysuser/admingrant");
		schema.addAction(action);

		return schema;
	}

	private FinderSchema createPatientSchema() {
		FinderSchema schema = new FinderSchema();
		schema.setCode(PATIENT_SCHEMA);
		schema.setTitle("病人管理");
		schema.setRelativePath("admin/hcrm/patient/list");

		FinderDataSet finderDataSet = new FinderDataSet("HCRM_PATIENT_SET");
		schema.setFinderDataSet(finderDataSet);

		FinderParam param = null;
		FinderColumn column = new FinderColumn();

		column.setLabel("编号");
		column.setColumn("id");
		column.setName("id");
		column.setType(DataType.Long);
		column.setPkey(true);
		column.setVisible(false);
		schema.addColumn(column);

		column = new FinderColumn();
		column.setLabel("住院号");
		column.setColumn("HOSPITALNUMBER");
		column.setName("HOSPITALNUMBER");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("姓名");
		column.setColumn("USERNAME");
		column.setName("USERNAME");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(50);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("性别");
		column.setColumn("SEX");
		column.setName("SEX");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(50);
		schema.addColumn(column);

		column = new FinderColumn();
		column.setLabel("电话");
		column.setColumn("TELEPHONE");
		column.setName("TELEPHONE");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("手机");
		column.setColumn("MOBILE");
		column.setName("MOBILE");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("科室");
		column.setColumn("DEPARTMENT");
		column.setName("DEPARTMENT");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("病种");
		column.setColumn("DISEASE");
		column.setName("DISEASE");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(50);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("出院诊断");
		column.setColumn("DISCHARGEDIAGNOSIS");
		column.setName("DISCHARGEDIAGNOSIS");
		column.setType(DataType.String);
		column.setVisible(false);
		column.setWidth(1);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("入院时间");
		column.setColumn("ADMISSIONTIME");
		column.setName("ADMISSIONTIME");
		column.setType(DataType.Date);
		column.setFormat(Configuration.getPatternDate());
		column.setVisible(true);
		column.setWidth(80);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.AfterThan);
		param.setFieldControl(FieldControl.Date);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("出院时间");
		column.setColumn("DISCHARGETIME");
		column.setName("DISCHARGETIME");
		column.setType(DataType.Date);
		column.setFormat(Configuration.getPatternDate());
		column.setVisible(true);
		column.setWidth(80);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.AfterThan);
		param.setFieldControl(FieldControl.Date);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("住院医生");
		column.setColumn("RESIDENTDOCTOR");
		column.setName("RESIDENTDOCTOR");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		FinderAction action = new FinderAction();
		action.setLabel("新增病人");
		action.setHref("admin/hcrm/patient/create");
		action.setTarget("dialog::{title: '新增病人', width:'650',height:'620'}");
		schema.addAction(action);

		action = new FinderAction();
		action.setLabel("编辑病人");
		action.setHref("admin/hcrm/patient/edit");
		action.setTarget("dialog::{title: '编辑病人', width:'650',height:'620'}");
		action.setMaxRowSelected(1);
		action.setMinRowSelected(1);
		schema.addAction(action);

		action = new FinderAction();
		action.setLabel("删除病人");
		action.setHref("admin/hcrm/patient/delete");
		action.setConfirm("您确定删除该病人吗？");
		action.setTarget("command");
		action.setMinRowSelected(1);
		schema.addAction(action);

		FinderDetailView detailView = new FinderDetailView();
		detailView.setCode("patientdetail");
		detailView.setLabel("回访明细");
		detailView.setHref("admin/hcrm/patient/detail");
		schema.addDetailView(detailView);

		schema.setEnableFilter(true);
		schema.setEnableSearch(true);
		return schema;
	}

	private FinderSchema createReturnVisitSchema() {
		FinderSchema schema = new FinderSchema();
		schema.setCode(VISIT_SCHEMA);
		schema.setTitle("回访信息管理");
		schema.setRelativePath("admin/hcrm/returnvisit/list");

		FinderDataSet finderDataSet = new FinderDataSet("HCRM_RETURNVISIT_SET");
		schema.setFinderDataSet(finderDataSet);

		FinderParam param = null;
		FinderColumn column = new FinderColumn();

		column.setLabel("编号");
		column.setColumn("id");
		column.setName("id");
		column.setType(DataType.Long);
		column.setPkey(true);
		column.setVisible(false);
		schema.addColumn(column);

		column = new FinderColumn();
		column.setLabel("病人住院号");
		column.setColumn("PATIENT_NUMBER");
		column.setName("PATIENT_NUMBER");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("病人姓名");
		column.setColumn("PATIENT_NAME");
		column.setName("PATIENT_NAME");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("病人科室");
		column.setColumn("PATIENT_DEPARTMENT");
		column.setName("PATIENT_DEPARTMENT");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("计划回访日期");
		column.setColumn("PLANVISITDATE");
		column.setName("PLANVISITDATE");
		column.setType(DataType.Date);
		column.setFormat(Configuration.getPatternDate());
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.AfterThan);
		param.setFieldControl(FieldControl.Date);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("实际回访日期");
		column.setColumn("ACTUALVISITDATE");
		column.setName("ACTUALVISITDATE");
		column.setType(DataType.Date);
		column.setFormat(Configuration.getPatternDate());
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.AfterThan);
		param.setFieldControl(FieldControl.Date);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("回访医生");
		column.setColumn("VISITDOCTOR_NAME");
		column.setName("VISITDOCTOR_NAME");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		param.setFieldControl(FieldControl.Text);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("是否完成");
		column.setColumn("COMPLETE");
		column.setName("COMPLETE");
		column.setType(DataType.Boolean);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Equals);
		param.setFieldControl(FieldControl.Checkbox);
		schema.addParam(param);

		FinderTabView tabView = new FinderTabView();
		tabView.setCode("todaynot");
		tabView.setLabel("今日未完成回访");
		tabView.setFilter(" TO_CHAR(PLANVISITDATE, 'YYYYMMDD') = TO_CHAR(sysdate, 'YYYYMMDD') and complete = 0 ");
		tabView.setOrder(0);
		schema.addTabView(tabView);

		tabView = new FinderTabView();
		tabView.setCode("today");
		tabView.setLabel("今日所有回访");
		tabView.setFilter(" TO_CHAR(PLANVISITDATE, 'YYYYMMDD') = TO_CHAR(sysdate, 'YYYYMMDD') ");
		tabView.setOrder(1);
		schema.addTabView(tabView);

		tabView = new FinderTabView();
		tabView.setCode("allnot");
		tabView.setLabel("所有未完成回访记录");
		tabView.setFilter("complete = 0");
		tabView.setOrder(2);
		schema.addTabView(tabView);

		tabView = new FinderTabView();
		tabView.setCode("alls");
		tabView.setLabel("所有回访记录");
		tabView.setFilter("1=1");
		tabView.setOrder(3);
		schema.addTabView(tabView);

		schema.setEnableFilter(true);
		schema.setEnableSearch(true);

		FinderAction action = new FinderAction();
		action.setLabel("处理回访信息");
		action.setHref("admin/hcrm/returnvisit/edit");
		action.setTarget("dialog::{title: '处理回访', width:'650',height:'400'}");
		action.setMaxRowSelected(1);
		action.setMinRowSelected(1);
		schema.addAction(action);

		action = new FinderAction();
		action.setLabel("删除回访记录");
		action.setHref("admin/hcrm/returnvisit/delete");
		action.setConfirm("您确定删除该回访记录吗？");
		action.setTarget("command");
		action.setMinRowSelected(1);
		schema.addAction(action);

		return schema;
	}
}
