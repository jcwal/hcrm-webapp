<#-- $Id: list.ftl 3512 2012-08-23 01:12:33Z jokeway $ -->
<@layout.ajaxContent title="医生列表 -[$Revision: 3512 $]" scripts="admin/macula-trial/finder-dialog/index.js">	
	<#assign code="doctor-list" />
	<@layout.content_head>
		 	
	</@layout.content_head>
		
	<@layout.content_main>	
		<div id="finder-dialog-content">
			<button id="edit-action-${code}" type="button" class="btn btn-has-icon" 
					data-bind="finder2dialog: content, finder: 'DOCTOR_SCHEMA', mapping:{'myid':'ID','mynickname':'NICKNAME'},
					staticParams:{'mynickname':'罗'}"
	  				href="javascript:void(0);" target="dialog::{title: '选择医生', width:'920',height:'350'}"
	  			>
	  			<span><span><i class="btn-icon"><@macula.themeImage src="bundle/btn_edit.gif"/></i>选择医生</span></span>
	  		</button>
	  		<p/>
	  		<table>
	  			<thead>
	  				<tr>
	  					<td>ID</td>
	  					<td>姓名</td>
	  					<td>myid</td>
	  					<td>mynickname</td>
	  				</tr>
	  			</thead>
	  			<tbody data-bind="template: { name: 'finder-data-tmpl-${code}' }"></tbody>
	  		</table>
	  		<p/>
	  		<button id="edit-action2-${code}" type="button" class="btn btn-has-icon" 
					data-bind="finder2dialog: myObject, finder: 'DOCTOR_SCHEMA', mapping:{'myid':'ID','mynickname':'NICKNAME'}"
	  				href="javascript:void(0);" target="dialog::{title: '选择单个医生', width:'920',height:'350'}"
	  			>
	  			<span><span><i class="btn-icon"><@macula.themeImage src="bundle/btn_edit.gif"/></i>选择单个医生</span></span>
	  		</button>
	  		<p/>
	  		<input type="text" data-bind="value: myObject.myid" /><input type="text" data-bind="value: myObject.mynickname" />
	  		<p/>
	  		<button id="edit-action2-${code}" type="button" class="btn btn-has-icon" 
					data-bind="finder2dialog: $root, finder: 'DOCTOR_SCHEMA', mapping:{'rootId':'ID','rootNickname':'NICKNAME'}"
	  				href="javascript:void(0);" target="dialog::{title: '选择根医生', width:'920',height:'350'}"
	  			>
	  			<span><span><i class="btn-icon"><@macula.themeImage src="bundle/btn_edit.gif"/></i>选择单个医生</span></span>
	  		</button>
	  		<p/>
	  		<input type="text" data-bind="value: rootId" /><input type="text" data-bind="value: rootNickname" />
	  		
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
							<td>
								${myid}
							</td>
							<td>
								${mynickname}
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
