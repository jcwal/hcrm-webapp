<#-- $Id: list.ftl 3512 2012-08-23 01:12:33Z jokeway $ -->
<@layout.ajaxContent title="医生列表 -[$Revision: 3512 $]" scripts="admin/macula-trial/finder-dialog/index.js">	
	<#assign code="doctor-list" />
	<@layout.content_head>
		 	
	</@layout.content_head>
		
	<@layout.content_main>	
		<div id="finder-dialog-content">
			<button id="edit-action-${code}" type="button" class="btn btn-has-icon" data-bind="finder2dialog: content, finder: 'DOCTOR_SCHEMA'"
	  				href="javascript:void(0);" target="dialog::{title: '选择医生', width:'920',height:'550'}"
	  			>
	  			<span><span><i class="btn-icon"><@macula.themeImage src="bundle/btn_edit.gif"/></i>选择医生</span></span>
	  		</button>
	  		<p/>
	  		<table>
	  			<thead>
	  				<tr>
	  					<td>ID</td>
	  					<td>姓名</td>
	  				</tr>
	  			</thead>
	  			<tbody data-bind="template: { name: 'finder-data-tmpl-${code}' }"></tbody>
	  		</table>
	  		<script id="finder-data-tmpl-${code}" type="text/x-jquery-tmpl">
				<#noparse>
					{{each(i,row) content}}
						<tr>
							<td>
								${ID}
							</td>
							<td>
								${NICKNAME}
							</td>
						</tr>
					{{/each}} 
				</#noparse>
			</script>
		</div>
		 
	</@layout.content_main>
	
	<@layout.content_foot>
	 
	</@layout.content_foot>
	
</@layout.ajaxContent>
