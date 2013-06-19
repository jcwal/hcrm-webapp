<@layout.ajaxContent title="<#if doctorId?exists>编辑医生信息<#else>新增医生信息</#if> -[$Revision: 3569 $]" scripts="admin/hcrm/doctor/edit.js">
	<#assign code="edit-doctor" />
	<@layout.content_main>	
	<div id="page-${code}" class="tableform">
		<form id="form-${code}" item-id="${doctorId?if_exists}" action="${base}/admin/hcrm/doctor/save" method="post">
			<input type="hidden" name="doctor.id" data-bind="value: id" />
			<div class="division">
				<table cellspacing="0" cellpadding="0" border="0">
					<tbody>
						<tr>
							<th><label>帐号：</label></th>
							<td>
								<#if doctorId?exists>
									<span data-bind="text: username"></span>
									<input type="hidden" name="doctor.username" data-bind="value: username"/>
								<#else>
								<input type="text" name="doctor.username" maxlength="50" data-bind="value: username" validate="required:true,rangelength:[1,20]" style="width: 180px;" />
								</#if>
							</td>
							<th><label>医生姓名：</label></th>
							<td><input type="text" name="doctor.nickname" maxlength="20" data-bind="value: nickname" validate="required:true,rangelength:[1,20]" style="width: 180px;" /></td>
						</tr>
						<#if !(doctorId?exists)>
						<tr>
							<th><label>密码：</label></th>
							<td><input type="password" name="doctor.password" id="password" maxlength="50" data-bind="value: password" validate="required:true" style="width: 180px;"/></td>
							<th><label>再次输入密码：</label></th>
							<td><input type="password" name="rePassword" maxlength="50" data-bind="value: rePassword" validate="required:true,equalTo:'#password'" style="width: 180px;"/></td>
						</tr>
						</#if>
						<tr>
							<th><label>岗位：</label></th>
							<td>
								<input name="doctor.title" data-bind="value: title" validate="rangelength:[0,20]" style="width: 180px;" />
							</td>
							<th><label>科室：</label></th>
							<td>
								<input name="doctor.department" data-bind="value: department" validate="rangelength:[0,20]" style="width: 180px;" />
							</td>									
						</tr>
						<tr>
							<th><label>电话：</label></th>
							<td>
								<input type="text" name="doctor.telephone" data-bind="value: telephone"  style="width: 180px;"  validate="rangelength:[0,20]"/>
							</td>
							<th><label>手机：</label></th>
							<td>
								<input type="text" name="doctor.mobile" data-bind="value: telephone"  style="width: 180px;"  validate="rangelength:[0,20]"/>
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