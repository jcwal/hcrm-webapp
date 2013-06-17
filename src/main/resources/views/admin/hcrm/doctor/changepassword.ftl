<@layout.ajaxContent title="修改密码 -[$Revision: 2477 $]" scripts="admin/hcrm/doctor/changepassword.js">
	<#assign code="change-password" />
	<@layout.content_head>
	</@layout.content_head>
	<@layout.content_main>
		<div id="page-${code}" class="tableform">
		<#if isAdmin?exists>
		<form id="form-${code}" action="${base}/admin/hcrm/doctor/adminsavepassword" method="post">
		<#else>
		<form id="form-${code}" action="${base}/admin/hcrm/doctor/savepassword" method="post">
		</#if>
			<input type="hidden" name="doctorId" value="${doctor.id}" />
			<div class="division">
				<table cellspacing="0" cellpadding="0" border="0">
					<tbody>
						<tr>
							<th><label>帐号：</label></th>
							<td>
								${doctor.username}
							</td>
						</tr>
						<#if !(isAdmin?exists)>
						<tr>
							<th><label>旧密码：</label></th>
							<td><input type="password" name="oldpassword" maxlength="50" validate="required:true" style="width: 200px;" /></td>
						</tr>
						</#if>
						<tr>
							<th><label>新密码：</label></th>
							<td>
								<input type="password" name="newpassword" id="newpassword" maxlength="50" validate="required:true" style="width: 200px;" />
							</td>
						</tr>
						<tr>
							<th><label>再次输入新密码：</label></th>
							<td>
								<input type="password" name="reNewpassword" maxlength="50" validate="required:true,equalTo:'#newpassword'" style="width: 200px;" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>			
			<div class="table-action">
				<button type="submit" class="btn btn-primary">
					<span><span>保存</span></span>
				</button>
				<#if isAdmin?exists>
				<button type="button" class="btn btn-secondary cancel-btn">
					<span><span>关闭</span></span>
				</button>
				</#if>
			</div>			
		</form>
		</div>
	</@layout.content_main>
	<@layout.content_foot>
	</@layout.content_foot>
</@layout.ajaxContent>
