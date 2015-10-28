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
<#-- $Id: finder-single-detail.ftl 4545 2013-11-01 04:36:42Z wilson $ -->
<@layout.ajaxContent title="${schema.title}-[$Revision: 4545 $]">
	<@layout.side_content>
		<#if (viewModel.detailViews)?exists && (viewModel.detailViews)?has_content>
			<div id="menu-desktop-${schema.code}" class="side-menu mlist">
				<div class="spage-side-nav">
					<ul>
	    				<#list viewModel.detailViews as detailView>
	            			<li class="l-handle" detail="${detailView.code}"><span>${detailView.label}</span></li>
	            		</#list>  
	    			</ul> 
				</div>
			</div>
			<script>
				$(function(){
					$('#menu-desktop-${schema.code} li').click(function(){
						var code = $(this).attr('detail');
						$('#menu-desktop-${schema.code} li').each(function(){
							var codex = $(this).attr('detail');
							if(codex == code){
								$(this).addClass('cur');
								$('#detail_' + codex).showme();
							} else {
								$(this).removeClass('cur');
								$('#detail_' + codex).hideme();
							}
						});
						return false;				
					}).first().trigger('click');
					$('#side').removeClass('hide');
				});
			</script>
		</#if>
	</@layout.side_content>
	<@layout.content_main>
		<#if (viewModel.detailViews)?exists && (viewModel.detailViews)?has_content>
			<#list viewModel.detailViews as detailView>
				<div></div>
				<div class="spage-main-box" id="detail_${detailView.code}" style="display: none;">
					<h3>${detailView.label}</h3>
					<@include_page path="/${detailView.href}" />
				</div>
				<div></div>
			</#list>
		</#if>
	</@layout.content_main>
</@layout.ajaxContent>