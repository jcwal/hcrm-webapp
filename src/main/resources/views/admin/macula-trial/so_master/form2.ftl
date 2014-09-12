<@layout.ajaxContent title="SO表单" scripts="admin/macula-trial/so_master/form2.js">
	<@layout.content_main>
	<table id="so_master_page2" width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td>
					<form class="tableform" item-id="${id?if_exists}" id="so_master_form2" action="${base}/admin/macula-trial/so_master/save" method="post">
						<div class="division">
							<input type="hidden" name="soMaster.id" data-bind="value: id" />
							<table cellspacing="0" cellpadding="0" border="0">
								<tbody>
									<tr>
										<th><label>销售单号：</label></th>
										<td><input type="text" name="soMaster.soNo" data-bind="value:soNo" validate="required:true,rangelength:[15,15]"/></td>
									</tr>
									<tr>
										<th><label>经销商：</label></th>
										<td><input type="text" name="soMaster.dealerNo" data-bind="value:dealerNo" validate="required:true,rangelength:[9,9]"/></td>
									</tr>
									<tr>
										<th><label>单类型：</label></th>
										<td><select name="soMaster.soType.code" data-bind="options: $Param('so_type_list','${base}'), optionsText:'label', optionsValue:'code', value: soType.code " /></td>
									</tr>
								</tbody>
							</table>
							<h4>明细信息</h4>
							<table class="gridlist">	
								<thead>
									<tr>
										<th width="40%">商品</th>
										<th width="20%">价格</th>
										<th width="20%">数量</th>
										<th width="10%">&nbsp;</th>
									</tr>
								</thead>
								<tbody data-bind="template: { name: 'soDetailTemplate2' }"></tbody>
								<tfoot>
									<tr>
										<td colspan="4">
											<button type="button" class="btn btn-has-icon" data-bind="click: onAddDetails">
								  				<span><span><i class="btn-icon"><@macula.themeImage src="bundle/btn_add.gif"/></i>新增</span></span>
								  			</button>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
						<div class="table-action">
							<button type="submit" class="btn btn-primary">
								<span><span>保存</span></span>
							</button>
							<button type="button" class="btn btn-secondary save-close-btn">
								<span><span>保存并关闭</span></span>
							</button>
							<button type="button" class="btn btn-secondary cancel-btn">
								<span><span>关闭</span></span>
							</button>							
						</div>
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	<#noparse>
	<script type="text/x-jquery-tmpl" id="soDetailTemplate2">
		{{each(index,detail) soDetails}}
		<tr data-bind="visible: !detail.deleted()" class="row {{if index % 2 == 0}}odd{{else}}even{{/if}}">
			<td>
				<input type="hidden" name="soMaster.soDetails[${index}].id" data-bind="value: detail.id" />
				<input type="hidden" name="soMaster.soDetails[${index}].deleted" data-bind="value: (detail.deleted())?1:0" />
				<input name="soMaster.soDetails[${index}].product.code" type="text" data-bind="tableOptions: 'product_info', optionsHeader:{'代码':'code', '名称':'label'}, optionsText: 'label', optionsValue: 'code', value: detail.product.code" validate="required:true" style="width:100%"/>
			</td>
			<td>
				<input type="text" name="soMaster.soDetails[${index}].price" data-bind="value: detail.price" validate="required:true,number:true" style="width:100%"/>
			</td>
			<td>
				<input type="text" name="soMaster.soDetails[${index}].qty" data-bind="value: detail.qty" validate="required:true,number:true" style="width:100%"/>
			</td>
			<td><input type="button" value="删除" data-bind="click: function(){ deleteModel(detail); }, clickBubble: false" /></td>
		</tr>
		{{/each}}
	</script>
	</#noparse>
	</@layout.content_main>
</@layout.ajaxContent>