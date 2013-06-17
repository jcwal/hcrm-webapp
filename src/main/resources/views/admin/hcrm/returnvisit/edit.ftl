<@layout.ajaxContent title="处理回访信息 -[$Revision: 3569 $]" scripts="admin/hcrm/returnvisit/edit.js">
	<#assign code="edit-returnvisit" />
	<@layout.content_main>	
	<div id="page-${code}" class="tableform">
		<form id="form-${code}" item-id="${returnvisitId?if_exists}" action="${base}/admin/hcrm/returnvisit/save" method="post">
			<div class="division">
				<input type="hidden" name="returnvisit.id" data-bind="value: id" />
				<table cellspacing="0" cellpadding="0" border="0">
					<tbody>
						<tr>
							<th><label>住院号：</label></th>
							<td>
								<span data-bind="text: patient.hospitalNumber"></span>
							</td>
							<th><label>病人姓名：</label></th>
							<td><span data-bind="text: patient.userName"></span></td>
						</tr>
						<tr>
							<th><label>病人电话：</label></th>
							<td>
								<span data-bind="text: patient.telephone"></span>
							</td>
							<th><label>病人手机：</label></th>
							<td><span data-bind="text: patient.mobile"></span></td>
						</tr>
						<tr>
							<th><label>病人地址：</label></th>
							<td colspan="3">
								<span data-bind="text: patient.address"></span>
							</td>
						</tr>
					</tbody>
				</table>
				<h4>回访信息</h4>
				<table cellspacing="0" cellpadding="0" border="0">
					<tbody>
						<tr>
							<th><label>计划回访日期：</label></th>
							<td>
								<input type="text" name="returnvisit.planVisitDate" data-bind="value: planVisitDate, type: 'date' " autocomplete="off" class="Wdate" onFocus="WdatePicker({dateFmt:'${datePattern}'})"  style="width: 170px;" readonly/>
							</td>
							<th><label>实际回访日期：</label></th>
							<td>
								<input type="text" name="returnvisit.actualVisitDate" data-bind="value: actualVisitDate, type: 'date' " autocomplete="off" class="Wdate" onFocus="WdatePicker({dateFmt:'${datePattern}'})"  style="width: 170px;"/>
							</td>
						</tr>
						<tr>
							<th><label>回访医生：</label></th>
							<td>
								<select name="returnvisit.visitDoctorId" data-bind="options: $Param('hcrm_doctor_list','${base}'), optionsCaption: '请选择...', optionsText: 'label', optionsValue:'code', value: visitDoctorId" style="width: 175px;" />
							</td>
							<th><label>是否完成回访：</label></th>
							<td>
								<input type="hidden" name="returnvisit.complete" data-bind="value: complete, type: 'boolean' " />
								<input type="checkbox" data-bind="checked: complete" />
							</td>
						</tr>
						<tr>
							<th><label>回访信息：</label></th>
							<td colspan="3">
								<textarea name="returnvisit.comment" rows="5" cols="50" maxlength="1024" data-bind="value: comment"  validate="rangelength:[0,1024]" style="width: 477px;" />
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