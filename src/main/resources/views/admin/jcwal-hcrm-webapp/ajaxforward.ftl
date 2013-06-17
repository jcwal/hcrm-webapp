<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<link rel="stylesheet" href="${resourceHost!""}/resources${appVersion!""}/admin/app-${AppuiVersion}/themes/${getTheme('skin')}/login${minVersion!""}.css" />
		<link rel="stylesheet" href="${resourceHost!""}/resources${appVersion!""}/admin/app-${AppuiVersion}/themes/${getTheme('skin')}/framework${minVersion!""}.css" />
		<link rel="stylesheet" href="${resourceHost!""}/resources${appVersion!""}/admin/app-${AppuiVersion}/themes/${getTheme('skin')}/style${minVersion!""}.css" />		
		<link rel="stylesheet" href="${resourceHost!""}/resources${appVersion!""}/admin/macula-ui-${MaculauiVersion}/themes/${getTheme('skin')}/macula-ui-${MaculauiVersion}${minVersion!""}.css" />
		<script>var base= '${base}', macula_ui='${resourceHost!""}/resources${appVersion!""}/admin/macula-ui-${MaculauiVersion}';</script>		
	</head>
	<body style="text-align: center;">
		<div style="margin-left:auto;margin-right:auto;">登录成功！</div>
		<script>
			setTimeout(function(){
				if(window.top.loginDialog){
					window.top.loginDialog.closeDialog('${(request.getUserPrincipal().getName())!}');
				}
			}, 1000);
		</script>
	</body>
</html>
