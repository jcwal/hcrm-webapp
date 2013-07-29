<@layout.ajaxContent title="<#if patientId?exists>编辑病人信息<#else>新增病人信息</#if> -[$Revision: 3569 $]" scripts="admin/hcrm/patient/edit.js">
	<#assign code="edit-patient" />
	<@layout.content_main>	
	<div id="page-${code}" class="tableform">
		<form id="form-${code}" item-id="${patientId?if_exists}" action="${base}/admin/hcrm/patient/save" method="post">
			<div class="division">
				<input type="hidden" name="patient.id" data-bind="value: id" />
				<table cellspacing="0" cellpadding="0" border="0">
					<tbody>
						<tr>
							<th><label>住院号：</label></th>
							<td>
								<input type="text" name="patient.hospitalNumber" maxlength="50" data-bind="value: hospitalNumber" validate="required:true,rangelength:[1,100]" style="width: 170px;" />
							</td>
							<th><label>病人姓名：</label></th>
							<td><input type="text" name="patient.userName" maxlength="20" data-bind="value: userName" validate="required:true,rangelength:[1,100]" style="width: 170px;" /></td>
						</tr>
						<tr>
							<th><label>性别：</label></th>
							<td>
								<input type="radio" name="patient.sex" value="男" data-bind="checked: sex"/>男&nbsp;&nbsp;
								<input type="radio" name="patient.sex" value="女" data-bind="checked: sex"/>女
							</td>
							<th><label>年龄：</label></th>
							<td><input type="text" name="patient.age" maxlength="3" data-bind="value: age" validate="required:true,digits:true" style="width: 170px;" /></td>
						</tr>
						<tr>
							<th><label>电话：</label></th>
							<td>
								<input type="text" name="patient.telephone" data-bind="value: telephone"  style="width: 170px;"  validate="rangelength:[0,20]"/>
							</td>
							<th><label>手机：</label></th>
							<td>
								<input type="text" name="patient.mobile" data-bind="value: mobile"  style="width: 170px;"  validate="rangelength:[0,20]"/>
							</td>							
						</tr>
						<tr>
							<th><label>住址：</label></th>
							<td colspan="3">
								<input type="text" name="patient.address" data-bind="value: address"  style="width: 477px;"/>
							</td>							
						</tr>
						<tr>
							<th><label>入院时间：</label></th>
							<td>
								<input type="text" id="admissionTime" name="patient.admissionTime" data-bind="value: admissionTime, type: 'date' " autocomplete="off" class="Wdate" onFocus="WdatePicker({dateFmt:'${datePattern}',maxDate:'#F{$dp.$D(\'dischargeTime\',{m:-1})}'})"  validate="required:true" style="width: 170px;"/>
							</td>
							<th><label>病种：</label></th>
							<td>
								<input type="text" name="patient.disease" data-bind="value: disease"  style="width: 170px;"/>
							</td>							
						</tr>
						<tr>
							<th><label>出院时间：</label></th>
							<td>
								<input type="text" id="dischargeTime" name="patient.dischargeTime" data-bind="value: dischargeTime, type: 'date' " autocomplete="off" class="Wdate" onFocus="WdatePicker({dateFmt:'${datePattern}',minDate:'#F{$dp.$D(\'admissionTime\',{m:1})}'})"  validate="required:true" style="width: 170px;"/>
							</td>
							<th><label>出院情况：</label></th>
							<td>
								<input type="text" name="patient.dischargeType" data-bind="value: dischargeType"  style="width: 170px;"/>
							</td>							
						</tr>
						<tr>
							<th><label>管床医生：</label></th>
							<td>
								<select name="patient.bedDoctorId" data-bind="options: $Param('hcrm_doctor_list','${base}'), optionsCaption: '请选择...', optionsText: 'label', optionsValue:'code', value: bedDoctorId" style="width: 175px;" />	
							</td>
							<th><label>科室：</label></th>
							<td>
								<input type="text" name="patient.department" data-bind="value: department"  style="width: 170px;"  validate="rangelength:[0,255]"/>
							</td>							
						</tr>
						<tr>
							<th><label>主治医生：</label></th>
							<td>
								<select name="patient.attendingDoctorId" data-bind="options: $Param('hcrm_doctor_list','${base}'), optionsCaption: '请选择...', optionsText: 'label', optionsValue:'code', value: attendingDoctorId" style="width: 175px;" />	
							</td>
							<th><label>住院医生：</label></th>
							<td>
								<select name="patient.residentDoctorId" data-bind="options: $Param('hcrm_doctor_list','${base}'), optionsCaption: '请选择...', optionsText: 'label', optionsValue:'code', value: residentDoctorId" style="width: 175px;" />	
							</td>							
						</tr>
						<tr>
							<th><label>出院诊断：</label></th>
							<td colspan="3">
								<textarea name="patient.dischargeDiagnosis" rows="2" cols="50" data-bind="value: dischargeDiagnosis" style="width: 477px;"></textarea>
							</td>
						</tr>
						<tr>
							<th><label>病理诊断：</label></th>
							<td colspan="3">
								<textarea type="text" name="patient.pathologicalDiagnosis" rows="2" cols="50" data-bind="value: pathologicalDiagnosis" style="width: 477px;"></textarea>
							</td>							
						</tr>
						<tr>
							<th><label>化验检查结果：</label></th>
							<td colspan="3">
								<textarea name="patient.treatment" rows="2" cols="50" data-bind="value: treatment" style="width: 477px;"></textarea>
							</td>
						</tr>
						<tr>
							<th><label>出院医嘱：</label></th>
							<td colspan="3">
								<textarea name="patient.dischargeAdvice" rows="2" cols="50" data-bind="value: dischargeAdvice" style="width: 477px;"></textarea>
							</td>
						</tr>
						<tr>
							<th><label>回访时间：</label></th>
							<td>
								<input type="text" name="patient.nextVisitTime" data-bind="value: nextVisitTime, type: 'date' " autocomplete="off" class="Wdate" onFocus="WdatePicker({dateFmt:'${datePattern}'})"  style="width: 170px;" />
							</td>
							<th><label>回访间隔(天)：</label></th>
							<td>
								<input type="text" name="patient.visitGap" data-bind="value: visitGap"  style="width: 170px;"  validate="required:true,number:true"/>
							</td>							
						</tr>
					</tbody>
				</table>
			</div>
			<div class="table-action">
				<button type="submit" class="btn btn-primary">
					<span><span>保存</span></span>
				</button>
				<button type="button" class="btn btn-secondary cancel-btn">
					<span><span>关闭</span></span>
				</button>
			</div>
		</form>		
	</div>
	</@layout.content_main>
</@layout.ajaxContent>