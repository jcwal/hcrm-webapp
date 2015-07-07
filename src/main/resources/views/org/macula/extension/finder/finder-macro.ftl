<#-- $Id: finder-macro.ftl 4860 2014-02-18 08:01:47Z wilson $ -->
<#macro finder_title schema>

</#macro>

<#macro finder_tabviews schema tabViewCode totals>
	<#if (schema.tabViews?exists && schema.tabViews?has_content) >
		<div id="finder-packet-${schema.code}" class="finder-packet">
			<div class="packet-items clearfix">
				<ul class="clearfix">
					<#list schema.tabViews as tabView>
					<li tabViewCode="${tabView.code}" url="${(relativePath)?if_exists}macula-mda/finder/${schema.code}/${tabView.code}" <#if tabView.code == tabViewCode>class="current"</#if>>
						<a href="javascript:void(0);" target="update" url="${(relativePath)?if_exists}macula-mda/finder/${schema.code}?tab=${tabView.code}">
							<span>${(tabView.label)!}(<em>${(totals[tabView.code])!}</em>)</span>
						</a>
					</li>
					</#list>
				</ul>
			</div>															
			<div class="scroll-handle l">
				<span>«</span>
			</div>
			<div class="scroll-handle r">
				<span>»</span>
			</div>
		</div>
	</#if> 
</#macro>

<#macro finder_actions schema searchOptions viewModel>
	<div class="gridlist-action finder-action clearfix" id="finder-action-${schema.code}" <#if (schema.enableFilter)>style="padding-right:75px;"</#if>>
	  <ul class="finder-action-items flt">
	  <li>
		<a id="finder-action-choice-${schema.code}" pkey="${(schema.pkey.name)?upper_case}">
			<span>
			确定选择
			</span>
		</a>
		</li>
	  </ul>
	  <form id="finder-action-form-${schema.code}" style="visibility:hidden;display:none" method="post">
	  		<input type="hidden" name="selection.finderCode" />
	  		<input type="hidden" name="selection.tabViewCode" />
	        <#if (viewModel.staticParams)?exists && (viewModel.staticParams)?has_content>
	         <#list viewModel.staticParams as staticParam>
	         	<input type="hidden" name="selection.staticParams[${staticParam_index}].name" value="${staticParam.name}" />
	         	<input type="hidden" name="selection.staticParams[${staticParam_index}].serializeValue" value="${(staticParam.serializeValue)?if_exists}" />
	         </#list>
	        </#if>
	  		<div style="visibility:hidden;display:none"></div>
	  </form>	   
	  <#if schema.enableFilter>
	  	<div class="finder-filter-action-handle">
	  		 <#if (schema.customFilterHref)?exists>
	   			<#local filterUrl="${schema.customFilterHref}?tab=${viewModel.tabViewCode!''}" >
	   		<#else>
	   			<#local filterUrl="${(relativePath)?if_exists}macula-mda/finder/${schema.code}/filter?tab=${viewModel.tabViewCode!''}">
	   		</#if>
	   		<#if (viewModel.staticParams)?exists && (viewModel.staticParams)?has_content>
		     <#list viewModel.staticParams as staticParam>
		     	<#local filterUrl=filterUrl+"&staticParams["+staticParam_index+"].name="+staticParam.name>
		     	<#local filterUrl=filterUrl+"&staticParams["+staticParam_index+"].serializeValue=${(staticParam.serializeValue)?if_exists}">
		     </#list>
		    </#if>
      		<a id="finder-filter-action-${schema.code}" href="javascript:void(0);" url="${filterUrl}">
				高级筛选<span>&raquo;</span>
  			</a>
      	</div>
	  </#if>
	  <#if schema.enableSearch>
		  <div class="frt" >
		    <form  id="finder-search-${schema.code}" class="finder-search" action="macula-mda/finder/${schema.code}" method="post" style="_display:inline;zoom:1;">
		      <input type="hidden" name="rows" />
	          <input type="hidden" name="page" />

	          <#if (viewModel.staticParams)?exists && (viewModel.staticParams)?has_content>
	          	<#list viewModel.staticParams as staticParam>
	          		<input type="hidden" name="staticParams[${staticParam_index}].name" value="${staticParam.name}" />
	          		<input type="hidden" name="staticParams[${staticParam_index}].serializeValue" value="${(staticParam.serializeValue)?if_exists}" />
	          	</#list>
	          </#if>
	          
		      <table cellpadding="0" cellspacing="0" style="width:auto">
		        <tr>
		          <td>				      
		            <span class="finder-search-select" style="cursor:pointer;" id="finder-keywords-handle-${schema.code}" dropmenu="finder-keywords-${schema.code}"><label>${(searchOptions[0].label)?if_exists}</label><@macula.maculauiImage src="arrow-down.gif" /></span>	
		          </td>
		          <td>
		          	<input type="hidden" name="filters[0].name" value="${(searchOptions[0].name)?if_exists}" />
		          	<#list searchOptions as param>
		          		<#if viewModel.getDefaultValue(param)?exists >
		          			<#local ddv = viewModel.getDefaultValue(param)/>
		          		<#else>
		          			<#local ddv = ''/>
		          		</#if>
		          		<div style="display:inline" class="searchWrapper ${param.name}">
	          			<#switch param.fieldControl>
		          			<#case 'Text'>
		          			<#case 'TextArea'>
		          			<#case 'MultiLine'>
		          				<input id="finder-search-${schema.code}-${param.name}" name="filters[0].value" value="${ddv?if_exists}" class="finder-search-input f-12 ${param.name}" type="text" search="true" autocomplete="off" size="15" maxlength="40" placeholder="输入后直接回车确认"/>
		          				<#break>
		          			<#case 'Password'>
		          				<input id="finder-search-${schema.code}-${param.name}" name="filters[0].value" value="${ddv?if_exists}" type="password" class="finder-search-input f-12 ${param.name}" type="text" search="true" autocomplete="off" size="15" maxlength="40" placeholder="输入后直接回车确认"/>
		          				<#break>
		          			<#case 'Combox'>
		          				<select id="finder-search-${schema.code}-${param.name}" name="filters[0].value" value="${ddv?if_exists}" class="finder-search-input f-12 ${param.name}" data-bind="options: $Param('${param.dataParamCode}','${base}'), optionsText:'label', optionsValue:'code'<#if param.allowNull>,optionsCaption:'请选择...'</#if>,value: ${param.name}" />
		          				<#break>
		          			<#case 'Radio'>
		          				<#break>
		          			<#case 'Date'>
		          				<input id="finder-search-${schema.code}-${param.name}" name="filters[0].value" value="${ddv?if_exists}" style="width:80px" autocomplete="off" class="finder-search-input x-input f-12 Wdate ${param.name}" type="text" onFocus="WdatePicker()" />						
		          				<#break>
		          			<#case 'DateTime'>
		          				<input id="finder-search-${schema.code}-${param.name}" name="filters[0].value" value="${ddv?if_exists}" style="width:140px" autocomplete="off" class="finder-search-input x-input f-12 Wdate ${param.name}" type="text" onFocus="WdatePicker({dateFmt:'${dateTimePattern}'})" />
		          				<#break>
		          			<#case 'RadioList'>
		          				<input id="finder-search-${schema.code}-${param.name}" type="hidden" name="filters[0].value" value="${ddv?if_exists}" class="${param.name}" data-bind="radiolist: $Param('${param.dataParamCode}','${base}'), optionsText:'label', optionsValue: 'code', value: ${param.name}" />
		          				<#break>
		          			<#case 'CheckboxList'>
		          				<input id="finder-search-${schema.code}-${param.name}" type="hidden" name="filters[0].value" value="${ddv?if_exists}" class="${param.name}" data-bind="checkboxlist: $Param('${param.dataParamCode}','${base}'), optionsText:'label', optionsValue: 'code', value: ${param.name}" />
		          				<#break>
		          			<#case 'CheckboxTree'>
		          				<input id="finder-search-${schema.code}-${param.name}" name="filters[0].value" value="${ddv?if_exists}" class="finder-search-input f-12 ${param.name}" data-bind="checkboxtree: '${param.dataParamCode}', optionsText:'label', optionsValue: 'code', value: ${param.name}" />
		          				<#break>
		          			<#case 'TableOption'>
		          				<input id="finder-search-${schema.code}-${param.name}" name="filters[0].value" value="${ddv?if_exists}" class="finder-search-input f-12 ${param.name}" data-bind="tableOptions: '${param.dataParamCode}', optionsText:'label', optionsValue: 'code', value: ${param.name}" />
		          				<#break>
		          			<#case 'TreeOption'>
		          				<input id="finder-search-${schema.code}-${param.name}" name="filters[0].value" value="${ddv?if_exists}" class="finder-search-input f-12 ${param.name}" data-bind="treeOptions: '${param.dataParamCode}', optionsText:'label', optionsValue: 'code', value: ${param.name}" />
		          				<#break>
		          		</#switch>
		          		</div>
		          	</#list>
		          	<script>
		          		ko.applyBindings(ko.mapping.fromJS({
		          			<#list searchOptions as param><#if viewModel.getDefaultValue(param)?exists ><#local ddv = viewModel.getDefaultValue(param)/><#else><#local ddv = ''/></#if>${param.name} : '${ddv?if_exists}' ,</#list>dummy : ''		          	
		          		}), $('#finder-search-${schema.code}')[0]);
		          	</script>      	
		          </td>
		          <td>
		          	<@macula.maculauiImage src="finder_search_btn.gif" class="finder-search-btn" />
		          </td>
		        </tr>
		      </table>
		      <div id="finder-keywords-${schema.code}" class="x-drop-menu" style="width:auto;border:1px #898989 solid;">
		        <ul class="group">
		        	<#list searchOptions as option>
		        		<li class="item" key="${option.name}">${option.label}</li>
		        	</#list>
		        </ul>
		      </div>		
		    </form>
		  </div>
	  </#if>	
		<div class="side-r hide" id="dlg-side-r" style="width:100%;float:right; background-color: aliceblue;">
			<div class="side-r-top clearfix">
				<b class="side-r-title flt f-14"></b>
				<span class="frt side-r-close pointer">
					<@macula.themeImage src="arrow_close.gif" />
				</span>
			</div>
			<div id="side-r-head" class="side-r-head" style="background-color: aliceblue;">
			</div>
			<div id="side-r-content" class="side-r-content" style="background-color: aliceblue;">
			</div>
			<div id="side-r-foot" class="side-r-foot" style="background-color: aliceblue;">
			</div>
		</div>
	</div>
</#macro>

<#macro finder_header schema viewModel>
	<div class="finder-header-wrapper" id="finder-header-${schema.code}">
		<table width="100%" cellpadding="0" cellspacing="0" class="finder-header">
			<colgroup>
				<#if schema.enableSelection>
				<col class="col-select">
				</#if>
				<#if !schema.hiddenDetailColumn>
				<col class="col-opt">
				</#if>
				<#list viewModel.columns as column>
					<#if column.visible>
					<col style="width: ${column.width}px" />
					</#if>
				</#list>
				<col></col>
			</colgroup>
			<thead>
	  			<tr>
	  				<#if schema.enableSelection>
	  				<td>
	  					<#if !schema.singleSelection>
	  					<div class="col-select-opt">
		  					<div id="col-select-opt-inner-${schema.code}" class="col-select-opt-inner" dropmenu="col-select-opt-items-${schema.code}">	
          						<input type="checkbox" class="sellist" onclick='this.blur()'/>
		  						<@macula.maculauiImage src="arrow-down.gif" />
		  					</div>
		  					<div id="col-select-opt-items-${schema.code}" class="col-select-opt-items x-drop-menu">
							  	  <ul class="group">
										<li class="item" onclick="$(this).trigger('${schema.code}_rowall_selected_');return false;">选择全部</li>
										<li class="item" onclick="$(this).trigger('${schema.code}_rowall_unselected_');return false;">无</li>
								  </ul>
							</div>
		  				</div>
		  				</#if>
	  				</td>
	  				</#if>
	  				<#if !schema.hiddenDetailColumn>
	  				<td class="col-opt"><div class="cell">查看</div></td>
	  				</#if>
	  				<#list viewModel.columns as column>
						<#if column.visible >
							<td>
							  <div class="cell" key="${column.name}" order="">
							    <table width="100%" cellpadding="0" cellspacing="0">
							    	<tr>
							    		<td style="cursor: pointer" title="自动换行" onclick="if(!$(this).attr('po')){$(this).attr('po',true);$('td[key=\'${column.name?upper_case}\'] div').css({'white-space':'inherit','word-break':'break-all'});} else {$(this).removeAttr('po');$('td[key=\'${column.name?upper_case}\'] div').css({'white-space':'','word-break':''});};"><div class='finder-col-label'>${column.label}</div></td>
							    		<td width='5px'><div class="finder-col-resizer">列宽</div></td> 
							    	</tr> 
							    </table>
							  </div>
							</td>
						</#if>
					</#list>	  				
	  			</tr>
	  		</thead>
	  	</table>
	</div>	
</#macro>

<#macro finder_body schema viewModel>
	<div class="hide" style="height: 26px;">&nbsp;</div>
	<div class="finder-tip" id="finder-tip-${schema.code}" style='visibility:hidden;'>
	 	<div class='selected'>
	 		您当前选定了<em></em>条记录！
	 		<span class="lnk" onclick="$(this).trigger('${schema.code}_rowall_unselected_');">取消选定</span>
			<span class="lnk" onclick="$(this).trigger('${schema.code}_rowall_selected_');">选定全部</span>
	 	</div>
	 	<div class='selectedall'>
			您当前选定了全部记录！共计<em></em>条。
			<span class="lnk" onclick="$(this).trigger('${schema.code}_rowall_unselected_');return false;">取消选定</span>
	 	</div>
	</div>
	
	<div id="finder-list-${schema.code}" class="finder-list finder-normal">
		<table width="100%" cellpadding="0" cellspacing="0">
	    	<colgroup>
	    		<#if schema.enableSelection>
				<col class="col-select">
				</#if>
				<#if !schema.hiddenDetailColumn>
				<col class="col-opt">
				</#if>
				<#list viewModel.columns as column>
					<#if column.visible>
					<col style="width: ${column.width}px" />
					</#if>
				</#list>
				<col></col>
			</colgroup>
			<tbody></tbody>
		</table>
		<div id="finder-nodata-${schema.code}" style="visibility:hidden;display:none;margin:20px;border:1px solid #999;background:#f0f0f0;padding:20px;-moz-border-radius:5px;-webkit-border-radius:5px;radius:5px">没有符合条件的条目.</div>
		<script id="finder-data-tmpl-${schema.code}" type="text/x-jquery-tmpl">
			<tr class="row" item-id="${r"${"}${(schema.pkey.name)?upper_case}}">
				<#if schema.enableSelection>
				<td>
					<div class="clearfix">
						<div class="span-auto">
							<#if !schema.singleSelection>
								<input type="checkbox" onclick="$(this).trigger('${schema.code}_row_selected_')" class="sel" name="items" rowindex="0" value="${r"${"}${(schema.pkey.name)?upper_case}}">
							<#else>
								<input type="radio" onclick="$(this).trigger('${schema.code}_row_choiced_')" class="sel" name="items" rowindex="0" value="${r"${"}${(schema.pkey.name)?upper_case}}">
							</#if>
						</div>
						<div class="flt">
							<#-- <i class="fav-star "><@macula.maculauiImage src="fav_start.png" /></i> -->
						</div>
					</div>
				</td>
				</#if>
				<#if !schema.hiddenDetailColumn>
				<td class="finder-list-command">
					<#if (viewModel.detailViews)?exists && (viewModel.detailViews)?has_content>
					<span title="展开查看" class="btn-detail-open" onclick="$(this).trigger('${schema.code}_item_detail_.${schema.code}')"><@macula.maculauiImage src="finder_drop_arrow.gif" /></span>
					<a title="在新窗口查看" href="javascript:void(0);" 
						<#if (schema.customDetailHref)?exists>
							url="${schema.customDetailHref}?tab=${(viewModel.tabViewCode)!""}&item=${r"${"}${(schema.pkey.name)?upper_case}}"
						<#else>
							url="${(relativePath)?if_exists}macula-mda/finder/${schema.code}/detail?tab=${(viewModel.tabViewCode)!""}&item=${r"${"}${(schema.pkey.name)?upper_case}}"
						</#if> 
						target="_blank"><@macula.maculauiImage src="new_window.gif" /></a>
					</#if>
				</td>
				</#if>
		    	<#list viewModel.columns as column>
					<#if column.visible >
						<td key="${column.name?upper_case}">
							<#if schema.enableHtmlOutput>
		    				<div class="cell">{{html ${column.name?upper_case} }}</div>
		    				<#else>
							<div class="cell">${r"${"}${column.name?upper_case}}</div>
							</#if>
		    			</td>	
					</#if>
				</#list>
			</tr>
		</script>
		<script id="finder-detail-tmpl-${schema.code}" type="text/x-jquery-tmpl">
			<tr class="finder-detail" id="finder-detail-${schema.code}">
				<td class="finder-detail-colspan">
					<div class="finder-detail-content clearfix"  id="finder-detail-content-${schema.code}"></div>
				</td>
			</tr>
		</script>
	</div>
</#macro>

<#macro finder_footer schema >
	<div id="finder-footer-${schema.code}" class="gridlist-footer finder-footer">
		<div class="clearfix" id="finder-pager-${schema.code}">
			<table>
				<tfoot>
					<tr>
			 			<td style="text-align:left;width:150px;border:none;padding:0;">
			   				<div class="clearfix">
		  						<div class="flt"><span> 每页最多显示：</span></div>
		  							<div id="finder-pageset-${schema.code}" class="flt pointer finder-pageset">
		  								<div class="finder-pageset-handle" dropmenu="finder-pagesel-${schema.code}">${schema.size}条</div>
		  								<div style="visibility:hidden;display:block;" class="x-drop-menu" id="finder-pagesel-${schema.code}">
											<div class="item">
		            							<input type="radio" id="finder_plimit_100" value="100" name="finder_plimit" />
		            							<label for="finder_plimit_100">100条</label>
		            						</div>
											<div class="item">
		            							<input type="radio" id="finder_plimit_50" value="50" name="finder_plimit" />
		            							<label for="finder_plimit_50">50条</label>
		            						</div>
		            						<div class="item">
		            							<input type="radio" id="finder_plimit_20" value="20" name="finder_plimit" />
		            							<label for="finder_plimit_20">20条</label>
		            						</div>
		            						<div class="item">
		            							<input type="radio" id="finder_plimit_10" value="10" name="finder_plimit" />
		            							<label for="finder_plimit_10">10条</label>
		            						</div>
	            						</div>
		  							</div>
		 						</div>
		 					</div>
			 			</td>
						<td style="text-align:left;width:150px">
							<label for="finder-jumpinput-${schema.code}"> 跳转到第<input type="text" id="finder-jumpinput-${schema.code}" style="width:20px; padding:0;border:1px #ccc solid;margin:0;font-size:12px;"></label>
						</td>
						<td style="text-align:center;">
							<div class="pager"><div class="pagernum"></div></div>
	    				</td>
			   	       <td style="text-align:right;">&nbsp;</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</#macro>