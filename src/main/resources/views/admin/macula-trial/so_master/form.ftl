<@layout.ajaxContent title="SO表单" scripts="admin/macula-trial/so_master/form.js">
	<@layout.content_main>
	<script id="soMaster" type="text/javascript">
		var samples_demo_soMaster = ${soMaster?default('""')};
	</script>
	<table id="so_master_page" width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td>
					<form class="tableform" item-id="${id?if_exists}" id="so_master_form" action="${base}/admin/macula-trial/so_master/save" method="post">
						<div class="division">
							<input type="hidden" name="soMaster.id" data-bind="value: id" data-vali/>
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
										<th>商品</th>
										<th>价格</th>
										<th>数量</th>
										<th>&nbsp;</th>
									</tr>
								</thead>
								<tbody data-bind="template: { name: 'soDetailTemplate' }"></tbody>
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
			<tr>
				<td>
					<form id="sub_so_detail_form" class="tableform">
						<h3>明细修改表单</h3>
						<div class="division">
							<table style="width:50%">
								<tr>
									<th>商品代码：</th>
									<td><input id="detailForm" type="text" data-bind="tableOptions: 'product_info', optionsHeader:{'代码':'code', '名称':'label'}, optionsText: 'label', optionsValue: 'code', optionsChange: onOptionsChange, value: selectedModel.product.code" validate="required:true" /></td>
								</tr>
								<tr>
									<th>价格：</th>
									<td><input type="text" data-bind="value: selectedModel.price" validate="required:true,number:true" /></td>
								</tr>
								<tr>
									<th>数量：</th>
									<td><input type="text" data-bind="value: selectedModel.qty" validate="required:true,digits:true"/></td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="table-action">
											<input type="button" value="取消修改" data-bind="enable: originModel() != null, click: cancelUpdate"/>
									   		<input type="button" value="确认修改" data-bind="enable: originModel() != null, click: updateModel"/>
									   		<input type="button" value="确认新增" data-bind="enable: originModel() == null, click: updateModel"/>
									   	</div>		
									</td>
								</tr>
							</table>
						</div>
					</form>
				</td>
			</tr>
	</tbody>
	</table>
	<#noparse>
	<script type="text/x-jquery-tmpl" id="soDetailTemplate">
		{{each(index,detail) soDetails}}
		<tr data-bind="visible: !detail.deleted(),click: function(){onRowSelect(detail)}" class="row {{if index % 2 == 0}}odd{{else}}even{{/if}}">
			<td>
				<input type="hidden" name="soMaster.soDetails[${index}].id" data-bind="value: detail.id" />
				<input type="hidden" name="soMaster.soDetails[${index}].deleted" data-bind="value: (detail.deleted())?1:0" />
				<span data-bind="text: detail.product.code"></span>
				<span data-bind="text: detail.product.label"></span>
				<input type="hidden" name="soMaster.soDetails[${index}].product.code" data-bind="value: detail.product.code" />
			</td>
			<td>
				<span data-bind="text: detail.price"></span>
				<input type="hidden" name="soMaster.soDetails[${index}].price" data-bind="value: detail.price" />
			</td>
			<td>
				<span data-bind="text: detail.qty"></span>
				<input type="hidden" name="soMaster.soDetails[${index}].qty" data-bind="value: detail.qty" />
			</td>
			<td><input type="button" value="删除" data-bind="enable: originModel() == detail, click: function(){ deleteModel(detail); }, clickBubble: false" /></td>
		</tr>
		{{/each}}
	</script>
	</#noparse>
	</@layout.content_main>
</@layout.ajaxContent>