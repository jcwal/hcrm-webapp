<#--

    Copyright 2010-2012 the original author or authors.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

            http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

-->
<#-- $Id: finder-ajax-detail.ftl 4545 2013-11-01 04:36:42Z wilson $ -->
<@layout.ajaxContent title="${schema.title} -[$Revision: 4545 $]">
	<#if (viewModel.detailViews)?exists && (viewModel.detailViews)?has_content>
		<div id="finder-detail-content-${schema.code}" class="finder-detail-content clearfix">
			<#if ((viewModel.detailViews)?size > 1) >
			<div class="tabs-wrap finder-tabs-wrap clearfix">
				<ul>
					<#list (viewModel.detailViews) as detailView >
					<li class="tab  <#if (detailView.code == detail)>current</#if>">
						<span><a href="javascript:void(0);"<#if (detailView.code != detail)>url="${(schema.relativePath)?if_exists}macula-mda/finder/${schema.code}/${detailView.code}?tab=${(viewModel.tabViewCode)!""}&item=${item}" target="update::{'container':'#finder-detail-content-${schema.code}'}"</#if>>${detailView.label}</a></span>
					</li>
					</#list>
				</ul>
			</div>
			</#if>
			<div>
				<@include_page path="/${schema.getDetailView(detail).href}" />
			</div>
		</div>
	</#if>
</@layout.ajaxContent>