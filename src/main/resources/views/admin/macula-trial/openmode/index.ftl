<@layout.ajaxContent title="打开方式示例" scripts="admin/macula-trial/openmode/index.js">
	<@layout.content_head>
		<div class="finder-title">
			<h2 class="head-title span-auto">打开方式示例（注意：这部分的文档在“界面层->打开方式”章节有详细的介绍。）</h2>
		</div>				
		<div class="gridlist-action">
			<button type="button" class="btn btn-has-icon" 
  				url="admin/macula-trial/openmode/blank"
  				href="javascript:void(0);" target="blank::{title: '新窗口打开', width:'350',height:'320'}"
  			>
  				<span><span><i class="btn-icon"><@macula.themeImage src="bundle/btn_edit.gif"/></i>新窗口打开</span></span>
  			</button>
  			<button type="button" class="btn btn-has-icon"
  				url="admin/macula-trial/openmode/dialog"
  				href="javascript:void(0);" target="dialog::{title: '对话框打开', width:'350',height:'320'}"
  			>
  				<span><span><i class="btn-icon"><@macula.themeImage src="bundle/btn_add.gif"/></i>对话框打开</span></span>
  			</button>
  			<button type="button" class="btn btn-has-icon"
  				url="admin/macula-trial/openmode/update"
  				href="javascript:void(0);" target="update"
  			>
  				<span><span><i class="btn-icon"><@macula.themeImage src="bundle/btn_add.gif"/></i>页面局部更新</span></span>
  			</button>
  			<button type="button" class="btn btn-has-icon" 
  				url="admin/macula-trial/openmode/replace"
  				href="javascript:void(0);" target="replace"
  			>
  				<span><span><i class="btn-icon"><@macula.themeImage src="bundle/btn_add.gif"/></i>页面局部替换</span></span>
  			</button>  			
  			<button id="commandBtn" type="button" class="btn btn-has-icon" 
  				url="admin/macula-trial/openmode/onCommand"
  				href="javascript:void(0);" target="command"
  			>
  				<span><span><i class="btn-icon"><@macula.themeImage src="bundle/btn_edit.gif"/></i>执行后触发事件</span></span>
  			</button>
  		</div>
  		
	</@layout.content_head>
	<@layout.content_main>
		<div>下面是执行结果反馈区，div的id为(execute-result)</div>	
		<div id="execute-result" style="height: 400px;width: 400px;border: 2px solid red;"></div>		
	</@layout.content_main>
	<@layout.content_foot>
		
	</@layout.content_foot>
	<script>
		$(function(){
			$(document.body).bind('after-demo-content-update', function(data) {
				alert('更新后执行了一个事件: after-demo-content-update');
			});
			$('#commandBtn').bind('onCommand', function(e, data) {
				alert('服务端返回的数据为：' + JSON.encode(data));
			});			
		});
	</script>
</@layout.ajaxContent>