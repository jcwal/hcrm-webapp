<#-- $Id: finder-filter.ftl 4782 2013-12-27 07:05:02Z wilson $ -->
<@layout.ajaxContent title="${viewModel.schema.title} -[$Revision: 4782 $]">
	<#assign schema = viewModel.schema />
	<#assign filterOptions = viewModel.filterOptions />
	<div id="side-r-head">
		<select id="filter-item-select-${schema.code}" style="width:100%">
	       <option>筛选项设置</option>
	       <optgroup label="筛选项">
	       		<#list filterOptions as option>
					<option value="${option.name}">
						${option.label}
						<#if (option.defaultFilter)?if_exists >√</#if>
					</option>
	       		</#list>
	        </optgroup>
	     	<optgroup label="全部">
	     	  <option value="_UNSELECTALL_">取消</option>
	     	  <option value="_SELECTALL_">使用</option>
	     	</optgroup>
		</select>     
	</div>
	<div id="side-r-content">
		<form id="finder-filter-${schema.code}" method="post" action="macula-mda/finder/${schema.code}">
			<input type="hidden" name="rows" />
			<input type="hidden" name="page" />

	        <#if (viewModel.staticParams)?exists && (viewModel.staticParams)?has_content>
	         <#list viewModel.staticParams as staticParam>
	         	<input type="hidden" name="staticParams[${staticParam_index}].name" value="${staticParam.name}" />
	         	<input type="hidden" name="staticParams[${staticParam_index}].serializeValue" value="${(staticParam.serializeValue)?if_exists}" />
	         </#list>
	        </#if>

	  		<div id='filter-list-${schema.code}' class="filter-list">
				<#list filterOptions as option>
					<dl k="${option.name}">
						<dt>
							<input type="hidden" name="filters[${option_index}].name" value="${option.name}"/>
							${option.label}
							<select name="filters[${option_index}].criteriaType" class="x-input-select inputstyle">
							<#list option.filterCriteriaTypes as type>
								<option value="${type}" <#if (option.defaultCriteriaType == type) >selected</#if>>${type.label}</option>
							</#list>
							</select>
							：
						</dt>
						<dd>
							<#assign param = option />
							<#if param.dataType == 'Integer' || param.dataType == 'Long'>
								<#assign typeValidator = 'digits:true' />
								<#assign nullValidator = 'digits:true' />
							<#elseif param.dataType == 'Double'>
								<#assign typeValidator = 'number:true' />
								<#assign nullValidator = 'number:true' />
							<#else>
								<#assign typeValidator = 'number:false' />
								<#assign nullValidator = 'number:false' />
							</#if>
							<#if !(param.allowNull)>
								<#if typeValidator?exists><#assign nullValidator = typeValidator+',required:true' /><#else><#assign nullValidator = 'required:true' /></#if>
							</#if>
							<#if viewModel.getDefaultValue(param)?exists >
				     			<#assign ddv = viewModel.getDefaultValue(param)/>
			       			<#else>
			          			<#assign ddv = ''/>
			     	  		</#if>
							<#if (param.dataParamCode)?exists >
								<#switch param.fieldControl>
				          			<#case 'Combox'>
				      					<select name="filters[${option_index}].value" value="${ddv?if_exists}" class="x-input" data-bind="options: $Param('${param.dataParamCode}','${base}'), optionsText:'label', optionsValue:'code' <#if param.allowNull>,optionsCaption:'请选择...'</#if>" validate="${nullValidator?if_exists}" />
				          				<#break>
				          			<#case 'Radio'>
				          				<#break>
				          			<#case 'RadioList'>
				          				<input type="hidden" name="filters[${option_index}].value" value="${ddv?if_exists}" class="x-input" data-bind="radiolist: $Param('${param.dataParamCode}','${base}'), optionsText:'label', optionsValue: 'code', value: ${param.name}" validate="${nullValidator?if_exists}" />
				          				<#break>
				          			<#case 'CheckboxList'>
				          				<input type="hidden" name="filters[${option_index}].value" value="${ddv?if_exists}" class="x-input" data-bind="checkboxlist: $Param('${param.dataParamCode}','${base}'), optionsText:'label', optionsValue: 'code', value: ${param.name}, cols:2" validate="${nullValidator?if_exists}"/>
				          				<#break>
				          			<#case 'CheckboxTree'>
				          				<input name="filters[${option_index}].value" value="${ddv?if_exists}" class="x-input" data-bind="checkboxtree: '${param.dataParamCode}', optionsText:'label', optionsValue: 'code', value: ${param.name}" validate="${nullValidator?if_exists}"/>
				          				<#break>
				          			<#case 'TableOption'>
				          				<input name="filters[${option_index}].value" value="${ddv?if_exists}" class="x-input" data-bind="tableOptions: '${param.dataParamCode}', optionsHeader: { '名称': 'label', '编码': 'code' }, optionsHeight:200, optionsText:'label', optionsValue: 'code', value: ${param.name}" validate="${nullValidator?if_exists}"/>
				          				<#break>
				          			<#case 'TreeOption'>
				          				<input name="filters[${option_index}].value" value="${ddv?if_exists}" class="x-input" data-bind="treeOptions: '${param.dataParamCode}', tmplKey: '${schema.code}-${param.dataParamCode}', optionsHeader: { '名称': 'label', '编码': 'code' }, optionsHeight:200, optionsText:'label', optionsValue: 'code', value: ${param.name}" validate="${nullValidator?if_exists}" style="width:100%"/>
				          				<#break>
				          		</#switch>
			          		<#else>
			          			<#switch param.fieldControl>
			          				<#case 'Date'>
										<span>大于等于</span>
										<input id="${schema.code}_${option.name}_1" name="filters[${option_index}].value" autocomplete="off" class="x-input Wdate" type="text"
											onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'${schema.code}_${option.name}_2\',{d:-1})}'})" 
											validate="${nullValidator?if_exists}" value="${ddv?if_exists}"
										/>
										<span>小于</span>
										<input id="${schema.code}_${option.name}_2" name="filters[${option_index}].anotherValue" autocomplete="off" class="x-input Wdate" type="text"
											onFocus="WdatePicker({minDate:'#F{$dp.$D(\'${schema.code}_${option.name}_1\',{d:1})}'})"
											validate="${typeValidator?if_exists}" 
										/>
										<#break>
									<#case 'DateTime'>
										<span>大于等于</span>
										<input id="${schema.code}_${option.name}_1" name="filters[${option_index}].value" autocomplete="off" class="x-input Wdate" type="text"
											onFocus="WdatePicker({dateFmt:'${dateTimePattern}',maxDate:'#F{$dp.$D(\'${schema.code}_${option.name}_2\',{m:-1})}'})" 
											validate="${nullValidator?if_exists}" value="${ddv?if_exists}"
										/>
										<span>小于</span>
										<input id="${schema.code}_${option.name}_2" name="filters[${option_index}].anotherValue" autocomplete="off" class="x-input Wdate" type="text"
											onFocus="WdatePicker({dateFmt:'${dateTimePattern}',minDate:'#F{$dp.$D(\'${schema.code}_${option.name}_1\',{m:1})}'})"
											validate="${typeValidator?if_exists}" 
										/>
										<#break>
									<#case 'Text'>										
										<span>大于等于</span><input name="filters[${option_index}].value" autocomplete="off" class="x-input" type="text" validate="${nullValidator?if_exists}" value="${ddv?if_exists}" />
										<span>小于</span><input name="filters[${option_index}].anotherValue" autocomplete="off" class="x-input" type="text" validate="${typeValidator?if_exists}"/>
										<#break>
									<#case 'Password'>
										<span>大于等于</span><input name="filters[${option_index}].value" autocomplete="off" class="x-input" type="password" validate="${nullValidator?if_exists}" value="${ddv?if_exists}" />
										<span>小于</span><input name="filters[${option_index}].anotherValue" autocomplete="off" class="x-input" type="password" validate="${typeValidator?if_exists}"/>
										<#break>
									<#case 'TextArea'>
									<#case 'MultiLine'>
										<textarea name="filters[${option_index}].value" autocomplete="off" class="x-input" validate="${nullValidator?if_exists}" value="${ddv?if_exists}"></textarea>
										<#break>									
								</#switch>
							</#if>
						</dd>
					</dl>
				</#list>
			</div>
		</form>
	</div>
	<div id="side-r-foot">
		<div class="table-action" style="height:auto;padding:0;margin:0;line-height:normal">
	    	<button class="btn btn-primary" style="width:70px" id="filter-submit-${schema.code}" type="button"><span><span>立即筛选</span></span></button>
	    	<button class="btn btn-secondary" style="width:70px" id="filter-reset-${schema.code}" type="button"><span><span>清除条件</span></span></button>
	    	<button class="btn btn-secondary" style="width:70px" id="filter-excel-${schema.code}" type="button"><span><span>导出Excel</span></span></button>
	    </div>
	</div>   
	<script>
		(function(){
			var filterModel = {
    			<#list filterOptions as param><#if viewModel.getDefaultValue(param)?exists ><#assign ddv = viewModel.getDefaultValue(param)/><#else><#assign ddv = ''/></#if>${param.name} : '${ddv?if_exists}' ,</#list>dummy : ''		          	
      		};
      		var filterViewModel = ko.mapping.fromJS(filterModel); 
			ko.applyBindings(filterViewModel, $('#finder-filter-${schema.code}')[0]);
			
			$('#filter-list-${schema.code} select[name$=criteriaType]').change(function(){
				var type = $(this).val();
				if(type == 'Between'){
					$(this).parent().next().find('span').showme();
					$(this).parent().next().find('input[name$=anotherValue]').showme().removeAttr('disabled');
				} else {
					$(this).parent().next().find('span').hideme();
					$(this).parent().next().find('input[name$=anotherValue]').hideme().attr('disabled','disabled').val('');
				}
				if($(this).find('option').size() <=1 ){
					$(this).hideme();
				}
			}).trigger('change');
			
			var tmp_str = "√";
			var filter_select = $('#filter-item-select-${schema.code}');		
			var filter_select_items = $(filter_select.find('optgroup')[0]); 
			filter_select.change(function(e){
				var selectValue = $(this).val();
				e.preventDefault();
				$($(this).find('option')[0]).attr('selected', 'selected');			
				 
				var options = filter_select_items.find('option');
				if( selectValue == '_SELECTALL_' ){
					options.each(function(){
						selectOption($(this));
					});
				} else if ( selectValue == '_UNSELECTALL_' ){
					options.each(function(){
						unselectOption($(this));
					});
				} else {
					var option = options.filter('[value=' + selectValue + ']');
					if( option.exists ) {
						isOptionSelected(option) ? unselectOption(option) : selectOption(option);
					}
				}
				options.each(function(){
					if( isOptionSelected($(this)) ){
						enableItem($(this).val());
					} else {
						disableItem($(this).val());
					}
				});	
			}).trigger('change');
			
			function isOptionSelected(option){
				return $.trim(option.text()).slice(-1) == tmp_str;
			}
			function selectOption(option){
				if(!isOptionSelected(option)){
					option.text( $.trim(option.text()) + tmp_str);
				}
			}		
			function unselectOption(option){
				if(isOptionSelected(option)){
					option.text( $.trim(option.text().replace(tmp_str, '')) );
				}
			}
			
			function enableItem(name){
				var filter_item = $('#filter-list-${schema.code} dl[k=' + name + ']');
				filter_item.showme();
				filter_item.find('select, input').removeAttr('disabled');
			}
			
			function disableItem(name){
				var filter_item = $('#filter-list-${schema.code} dl[k=' + name + ']');
				filter_item.hideme();
				filter_item.find('select, input').attr('disabled', 'disabled');
			}
			
			$('#filter-list-${schema.code} dl').hover(function(){
				$(this).addClass('over');		
			},function(){
				$(this).removeClass('over');
			});
			$('#filter-reset-${schema.code}').click(function(){
				$('#finder-filter-${schema.code}')[0].reset();
				ko.mapping.updateFromJS(filterViewModel,filterModel);
			});		
		})();
	</script>
</@layout.ajaxContent>