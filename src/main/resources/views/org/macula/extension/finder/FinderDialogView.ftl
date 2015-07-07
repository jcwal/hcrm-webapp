<#-- $Id: FinderView.ftl 4708 2013-12-02 09:52:00Z wilson $ -->
<@layout.ajaxContent title="${viewModel.schema.title}-[$Revision: 4708 $]">
	<#import "/org/macula/extension/finder/finder-macro.ftl" as finder />
	<@layout.content_head>
		<@finder.finder_title schema=viewModel.schema />
		<@finder.finder_tabviews schema=viewModel.schema tabViewCode=(viewModel.tabViewCode)! totals=viewModel.totals />
		<@finder.finder_actions schema=viewModel.schema searchOptions=viewModel.searchOptions! viewModel=viewModel/>
		<@finder.finder_header schema=viewModel.schema viewModel=viewModel />
	</@layout.content_head> 
	<div class="content-main" style="height:auto;" base="${base}"><#compress>
		<@finder.finder_body schema=viewModel.schema viewModel=viewModel/>	
	</#compress></div>		
	<@layout.content_foot>
		<@finder.finder_footer schema=viewModel.schema />
		<#if (viewModel.schema.resources)?has_content>
			<#list viewModel.schema.resources as resource>
				<#if resource.type != "javascript"><link rel="stylesheet" type="text/css" href="${base}/views${appVersion!""}/${resource.href}" /></#if>
			</#list>
		</#if>
	</@layout.content_foot>
	<#if (viewModel.schema.resources)?has_content>
		<#list viewModel.schema.resources as resource>
			<#if resource.type == "javascript"><script type="text/javascript" src="${base}/views${appVersion!""}/${resource.href}"></script></#if>
		</#list>
	</#if>
	<script>$.maculafinder('${viewModel.schema.code}',{relativePath: '${relativePath}', pageSize:${viewModel.schema.size}, queryOnLoad: <#if viewModel.schema.queryOnLoad>true<#else>false</#if>});</script>
</@layout.ajaxContent>