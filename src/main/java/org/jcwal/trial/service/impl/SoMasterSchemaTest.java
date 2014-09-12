/**
 * SoMasterSchemaTest.java 2011-5-31
 */
package org.jcwal.trial.service.impl;

import javax.annotation.PostConstruct;

import org.macula.base.data.vo.CriteriaType;
import org.macula.base.data.vo.DataType;
import org.macula.base.data.vo.FieldControl;
import org.macula.plugins.mda.finder.domain.FinderAction;
import org.macula.plugins.mda.finder.domain.FinderColumn;
import org.macula.plugins.mda.finder.domain.FinderDataSet;
import org.macula.plugins.mda.finder.domain.FinderDetailView;
import org.macula.plugins.mda.finder.domain.FinderParam;
import org.macula.plugins.mda.finder.domain.FinderResource;
import org.macula.plugins.mda.finder.domain.FinderSchema;
import org.macula.plugins.mda.finder.service.FinderSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p> <b>SoMasterSchemaTest</b> 是SoMaster的FinderSchema模拟数据. </p> </p>
 * 
 * @since 2011-5-31
 * @author Wilson Luo
 * @version $Id: SoMasterSchemaTest.java 3577 2012-09-12 01:18:50Z wzp $
 */

@Component
public class SoMasterSchemaTest {

	@Autowired
	private FinderSchemaService memorySchemaRepository;

	@PostConstruct
	public void registry() {
		memorySchemaRepository.add(SoMasterSchemaTest.schema);
	}

	public static final FinderSchema schema = new FinderSchema();
	static {
		schema.setCode("SO_MASTER_DATASET");
		schema.setTitle("销售单列表");
		schema.setRelativePath("admin/macula-trial/so_master/index");

		schema.setEnableFilter(true);
		schema.setEnableSearch(true);

		FinderDataSet finderDataSet = new FinderDataSet("SO_MASTER_SET");
		schema.setFinderDataSet(finderDataSet);

		FinderParam param = null;
		FinderColumn column = new FinderColumn();

		column.setLabel("编号");
		column.setColumn("id");
		column.setName("id");
		column.setType(DataType.Long);
		column.setVisible(true);
		column.setWidth(100);
		column.setPkey(true);
		schema.addColumn(column);

		column = new FinderColumn();
		column.setLabel("销售单号");
		column.setColumn("so_no");
		column.setName("so_no");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(200);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.In);
		param.setFieldControl(FieldControl.MultiLine);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("经销商号");
		column.setColumn("dealer_no");
		column.setName("dealer_no");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(200);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Contains);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("销售单类型");
		column.setColumn("sotype");
		column.setName("sotype");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(60);
		schema.addColumn(column);

		param = new FinderParam(column, "so_type_list");
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.In);
		param.setFieldControl(FieldControl.CheckboxList);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("创建人");
		column.setColumn("created_by");
		column.setName("created_by");
		column.setType(DataType.String);
		column.setVisible(true);
		column.setWidth(100);
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.StartWith);
		schema.addParam(param);

		column = new FinderColumn();
		column.setLabel("创建时间");
		column.setColumn("created_time");
		column.setName("created_time");
		column.setType(DataType.Timestamp);
		column.setVisible(true);
		column.setWidth(160);
		column.setFormat("yyyy-MM-dd HH:mm:ss");
		schema.addColumn(column);

		param = new FinderParam(column, null);
		param.setEnableSearch(true);
		param.setEnableFilter(true);
		param.setDefaultCriteriaType(CriteriaType.Between);
		param.setFieldControl(FieldControl.DateTime);
		schema.addParam(param);

		FinderAction action = new FinderAction();
		action.setLabel("新增销售单");
		action.setTarget("dialog::{title:'新增销售单',width:'800',height:'600',type:'GET'}");
		action.setHref("admin/macula-trial/so_master/new");
		schema.addAction(action);

		action = new FinderAction();
		action.setLabel("编辑销售单(DLG)");
		action.setMinRowSelected(1);
		action.setMaxRowSelected(1);
		action.setHref("admin/macula-trial/so_master/edit/");
		action.setTarget("dialog::{title:'编辑销售单',width:'800',height:'600',type:'POST'}");
		schema.addAction(action);
		
		action = new FinderAction();
		action.setLabel("编辑销售单(INLINE)");
		action.setMinRowSelected(1);
		action.setMaxRowSelected(1);
		action.setHref("admin/macula-trial/so_master/edit2/");
		action.setTarget("dialog::{title:'编辑销售单',width:'800',height:'600',type:'POST'}");
		schema.addAction(action);

		action = new FinderAction();
		action.setLabel("编辑销售单(WIN)");
		action.setMinRowSelected(1);
		action.setMaxRowSelected(1);
		action.setHref("admin/macula-trial/so_master/edit/");
		action.setTarget("blank::{title:'编辑销售单',width:'800',height:'600',type:'POST'}");
		schema.addAction(action);

		action = new FinderAction();
		action.setLabel("删除销售单");
		action.setHref("admin/macula-trial/so_master/delete/");
		action.setMinRowSelected(1);
		action.setConfirm("您确定要删除吗？");
		action.setTarget("command::{callback: do_so_master_finder_delete}");
		schema.addAction(action);

		action = new FinderAction();
		action.setLabel("导出EXCEL");
		action.setHref("admin/macula-trial/so_master/excel.xls");
		action.setMinRowSelected(1);
		action.setConfirm("您确定要导出EXCEL吗？");
		action.setTarget("blank");
		schema.addAction(action);

		action = new FinderAction();
		action.setLabel("导出EXCEL2");
		action.setHref("admin/macula-trial/so_master/excel2.xls");
		action.setMinRowSelected(1);
		action.setConfirm("您确定要导出EXCEL吗？");
		action.setTarget("blank");
		schema.addAction(action);

		FinderDetailView detailView = new FinderDetailView();
		detailView.setCode("detail1");
		detailView.setLabel("基本信息");
		detailView.setHref("admin/macula-trial/openmode/dialog");
		schema.addDetailView(detailView);

		detailView = new FinderDetailView();
		detailView.setCode("detail2");
		detailView.setLabel("附加信息");
		detailView.setHref("admin/macula-trial/openmode/dialog");
		schema.addDetailView(detailView);

		schema.addResource(FinderResource.createJavascript("js", "admin/macula-trial/so_master/custom_finder.js"));

	}
}
