<@layout.ajaxContent title="SO表单" scripts="admin/macula-trial/so_master/form.js">
	<@layout.content_main>
 
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td>
					<form class="tableform" action="${base}/admin/uploadDocument/save" enctype="multipart/form-data" method="post">
						<div class="division">
							<table cellspacing="0" cellpadding="0" border="0">
								<tbody>
									<tr>
										<th><label>上传人：</label></th>
										<td><input type="text" name="document.uploadUserName" value="admin123" /></td>
									</tr>
									<tr>
										<th><label>有效开始时间：</label></th>
										<td><input type="text" name="document.effectiveDate" value="2015-07-07"/></td>
									</tr>
									<tr>
										<th><label>文件：</label></th>
										<td><input type="file" name="documentFile" /></td>
									</tr>
									<tr>
										<th> </th>
										<td><input type="submit"/></td>
									</tr>
								</tbody>
							</table>
						</div>
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	</@layout.content_main>
</@layout.ajaxContent>