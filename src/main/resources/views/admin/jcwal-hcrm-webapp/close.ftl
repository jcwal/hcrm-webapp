<!DOCTYPE html>
<html>
<head>
	<style>
		.w {width: 980px; padding: 0; margin: 0 auto; zoom: 1;}
		#info .success {background-color: #E9FDE9; height: 300px; font-size: 14px; border: 1px solid #090; line-height: 25px; padding: 2px 10px 2px 23px; margin: 50px 0 0 20px;}
		#info .success h2 {margin-top: 15px;font-size: 16px;}
		#info hr {background-color: #D1D7DC;border: medium none;color: #D1D7DC;height: 1px; margin: 5px 0 15px;}
	</style>
</head>
<body>
	<div id="info" class="w">
		<div id="msg" class="success">
		    <div class="msg-inner">
		        <div class="msg-content">
		            <h2>退出成功</h2>
		            <hr>
		            <p>您已成功退出应用系统</p>
		            <p>出于安全考虑，请您<a href="javascript:closeWin();">关闭</a>所有浏览器窗口。</p>
		            <p>或者点击<a href="${base}/j_spring_security_logout">重新登录</a></p>
		        </div>
		    </div>
		</div>
	</div>
		
	<script type="text/javascript">
		function closeWin() {
			window.opener =null;
			window.open('', '_self', '');
			window.close();
		};
		closeWin();
	</script>	
</body>
</html>