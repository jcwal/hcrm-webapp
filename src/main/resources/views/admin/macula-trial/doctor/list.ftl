<#-- $Id: list.ftl 3512 2012-08-23 01:12:33Z jokeway $ -->
<@layout.ajaxContent title="医生列表 -[$Revision: 3512 $]" scripts="admin/macula-trial/doctor/list.js">	
	<#assign code="doctor-list" />
	<@layout.content_head>
		<h2 class="head-title">医生列表</h2>
		<div id="finder-actions-${code}" class="gridlist-action">
			<div class="flt">
				<button id="add-action-${code}" type="button" class="btn btn-has-icon"
	  				href="javascript:void(0);" target="dialog::{title: '新增医生', width:'720',height:'250'}"
	  				url="admin/macula-trial/doctor/create"
	  			>
	  				<span><span><i class="btn-icon"><@macula.themeImage src="bundle/btn_add.gif"/></i>新增医生</span></span>
	  			</button>
	  			<button id="edit-action-${code}" type="button" class="btn btn-has-icon" data-bind="click: onEditAction, enable: onEditEnable"
	  				href="javascript:void(0);" target="dialog::{title: '编辑医生', width:'720',height:'250'}"
	  			>
	  				<span><span><i class="btn-icon"><@macula.themeImage src="bundle/btn_edit.gif"/></i>编辑医生</span></span>
	  			</button>
	  			<button id="delete-action-${code}" type="button" class="btn btn-has-icon" data-bind="click: onDeleteAction, enable: onDeleteEnable"
	  				href="javascript:void(0);" target="command"
	  			>
	  				<span><span><i class="btn-icon"><@macula.themeImage src="bundle/delete.gif"/></i>删除医生</span></span>
	  			</button>
				<button type="button" class="btn btn-has-icon" data-bind="click: onRefreshClick">
	  				<span><span><i class="btn-icon"><@macula.themeImage src="bundle/finder-refresh.gif"/></i>刷新</i></span></span>
	  			</button>
	  		</div>
  			<div class="frt">
  				<form id="finder-search-${code}" method="get" action="${base}/admin/macula-trial/doctor/listDatas">
					<input type="hidden" name="rows" value="2" />
					<input type="hidden" name="page" value="1" />	
			    </form>	
  			</div>
  		</div>  		
	</@layout.content_head>
		
	<@layout.content_main>	
		
		
		<div id="finder-list-${code}" >
			<table width="100%" cellpadding="0" cellspacing="0" class="treeTable gridlist">
	    		<thead>
					<th width="5%">编号</th>
					<th width="15%">账号</th>
					<th width="20%">姓名</th>
					<th width="20%">岗位</th>
					<th width="20%">电话</th>
					<th width="20%">手机</th>
				</thead>
				<tbody data-bind="template: { name: 'finder-data-tmpl-${code}' }"></tbody>
			</table>
			<div id="finder-nodata-${code}" style="visibility:hidden;display:none;margin:20px;border:1px solid #999;background:#f0f0f0;padding:20px;-moz-border-radius:5px;-webkit-border-radius:5px;radius:5px">没有符合条件的条目.</div>
			<script id="finder-data-tmpl-${code}" type="text/x-jquery-tmpl">
				<#noparse>
					{{each(i,row) content}}
						<tr id="doctor-list-r${id}" class="{{if i%2 == 0}}even{{else}}odd{{/if}}" data-bind="click: function(e) { onRowClick(e,row); }">
							<td>
								<a  href="javascript:void(0);"					
									url="admin/macula-trial/doctor/edit/${id}" target="dialog::{title: '编辑医生', width:'720',height:'250'}">
									${id}
								</a>
							</td>
							<td>${username}</td>
							<td>${nickname}</td>					
							<td>${title}</td>
							<td>${telephone}</td>
							<td>${mobile}</td>
						</tr>
					{{/each}} 
				</#noparse>
			</script>
		</div>
	</@layout.content_main>
	
	<@layout.content_foot>
		<@layout.content_pager "${code}"/>
	</@layout.content_foot>
	
</@layout.ajaxContent>
