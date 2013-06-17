<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<link rel="icon" href="${resourceHost!""}/resources${appVersion!""}admin//app-${AppuiVersion}/themes/${getTheme('skin')}/images/favicon.ico" type="image/x-icon" />
	    <link rel="icon" href="${resourceHost!""}/resources${appVersion!""}/admin/app-${AppuiVersion}/themes/${getTheme('skin')}/images/favicon.gif" type="image/gif" />
		<link rel="stylesheet" href="${resourceHost!""}/resources${appVersion!""}/admin/app-${AppuiVersion}/themes/${getTheme('skin')}/login/regist${minVersion!""}.css" />
		<link rel="stylesheet" href="${resourceHost!""}/resources${appVersion!""}/admin/app-${AppuiVersion}/themes/${getTheme('skin')}/login/regist.entry${minVersion!""}.css" />	
		<script>var base= '${base}', macula_ui='${resourceHost!""}/resources${appVersion!""}/admin/macula-ui-${MaculauiVersion}';</script>
		<script type="text/javascript" src="${resourceHost!""}/resources${appVersion!""}/admin/jquery-${JQueryVersion}${minVersion!""}.js"></script>
		<script type="text/javascript" src="${resourceHost!""}/resources${appVersion!""}/admin/jquery.tmpl${minVersion!""}.js"></script>
		<script type="text/javascript" src="${resourceHost!""}/resources${appVersion!""}/admin/json2${minVersion!""}.js"></script>
		<script type="text/javascript" src="${resourceHost!""}/resources${appVersion!""}/admin/knockout-1.2.1${minVersion!""}.js"></script>
		<script type="text/javascript" src="${resourceHost!""}/resources${appVersion!""}/admin/knockout.mapping${minVersion!""}.js"></script>
		<script type="text/javascript" src="${resourceHost!""}/resources${appVersion!""}/admin/macula-ui-${MaculauiVersion}/macula-ui-${MaculauiVersion}${minVersion!""}.js"></script>		
		<script type="text/javascript" src="${resourceHost!""}/resources${appVersion!""}/admin/macula-ui-${MaculauiVersion}/macula-ui-${MaculauiVersion}${minVersion!""}.js"></script>
		<script type="text/javascript" src="${base}/views${appVersion!""}/admin/jcwal-hcrm-webapp/login${minVersion!""}.js"></script>
		<title>用户登录</title>
	</head>
	<body>
		<!-- 如果是统一认证后本地出错，则统一登出后跳回统一认证登录界面 -->
		<#--
		<#if exception?exists && !(RequestParameters.form)?exists>
			<script type="text/javascript">
				alert('${getText(exception.getMessage(), exception.getMessage())}');
				window.location = '${maculaConfig.getCasServerService()}/logout?service=${maculaConfig.getCasClientService()}/j_spring_security_logout&forward=/admin';
			</script>
		</#if>
		-->
		<!-- 检测是否需要统一认证 -->
		<#--
		<#if !(RequestParameters.form)?exists && !exception?exists>
			<script type="text/javascript">
				function checkCasLogin(status) {
					window.location = '${maculaConfig.getCasServerService()}/login?service=${maculaConfig.getCasClientService()}/j_spring_cas_security_check<#if (RequestParameters.forward)?exists>&forward=${RequestParameters.forward}</#if>&form=${maculaConfig.getCasClientService()}/admin/login';
				}
			</script>	
			<script type="text/javascript" src="${maculaConfig.getCasServerService()}/v?jsonp=checkCasLogin&appId=${maculaConfig.getAppName()}"></script>
		</#if>	
		-->
	<#if !(RequestParameters.forward)?exists || !RequestParameters.forward?contains("/ajaxforward")>
		<div id="logo">
			<div>
				
			</div>
		</div>		
	<div id="entry" class="w">
		<div class="mt">
			<h2>用户登录</h2>
			<b></b>
		</div>
		<div style="padding-top:20px;" class="mc">
	<#else>
	<div id="entry">
		<div style="padding-top:20px;" class="mc_ajax">
	</#if>	
		
			<form id="localLoginForm" method="post" action="${base}/j_spring_security_check?form=true<#if (RequestParameters.forward)?exists>&forward=${RequestParameters.forward}</#if>">	
			<input type="hidden" id="captchaId" name="captchaId" value="${captchaId}" />		
			<div class="form">
				<div class="item">
					<span class="label">用户名：</span>
					<div class="fl">
						<input id="userName" name="j_username" type="text" class="text" tabindex="1" sta="0" validate="{required:true,messages:{required: '请输入用户名！'}}"/>						
						<label id="userName_succeed" class="blank invisible"></label>
						<span class="clr"></span>
						<label id="userName_error"></label>
					</div>
				</div>
				<div class="item">
					<span class="label">密　码：</span>
					<div class="fl">
						<input id="password" name="j_password" type="password" tabindex="2" class="text" validate="{required:true,messages:{required: '请输入密码！'}}"/>
						<label id="password_succeed" class="blank invisible"></label>
						<span class="clr"></span>
						<label id="password_error"></label>
						<label id="caps-info" class="caps-info"></label>
					</div>
				</div>
				<#if captchaStatus?exists && captchaStatus.inViolation >
				<div id="o-authcode" class="item">
					<span class="label">验证码：</span>
					<div class="fl">
						<input id="captchaResponse" name="captchaResponse" type="text" tabindex="6" maxlength="4" class="text text-1" validate="{required:true,messages:{required: '请输入验证码！'}}"/>
						<label class="img">
							<img id="loginCode" src="${base}/captcha?id=${captchaId}&_t=${captchaStatus.lastUpdate}" title="点击更换验证码！" onfocus="this.blur();" style="cursor:pointer;width:100px;height:26px;" align="absMiddle"/>						
						</label>
						<label class="ftx23">&nbsp;看不清？<a class="flk13" id="loginCode_a" href="javascript:void(0)">换一张</a></label>
						<label id="captchaResponse_succeed" class="blank invisible" ></label>
						<span class="clr"></span>
						<label id="captchaResponse_error"></label>
					</div>
				</div>
				</#if>
				<div id="autoentry" class="item">
					<span class="label">&nbsp;</span>
					<div class="fl">
						<label class="mar">
							<input type="checkbox" name="chkRememberUsername" id="chkRememberUsername" checked="checked" class="checkbox" />
							记住用户名
						</label>
					</div>	
				</div>
				<div class="item">
					<span class="label">&nbsp;</span>				
					<input type="submit" tabindex="8" value="登录" id="loginsubmit" class="btn-img btn-entry">
					<#if exception?exists >
						<label class="errorTip">${getText(exception.getMessage(), exception.getMessage())}</label>
			    	</#if>					
				</div>
				<#--	
				<div class="item extra">
					<label class="ftx24">
					<#if (RequestParameters.form)?exists>
					使用统一认证中心帐号登录系统：
					<span class="clr"></span>
						<span class="btns localapp"><s></s><a onclick="window.location='${maculaConfig.getCasServerService()}/login?service=${maculaConfig.getCasClientService()}/j_spring_cas_security_check<#if (RequestParameters.forward)?exists>&forward=${RequestParameters.forward}</#if>&form=${maculaConfig.getCasClientService()}/admin/login';">统一认证中心登录</a></span>
					</label>
					</#if>
				</div>
				-->
			</div>	
			<#if (bindingResult.fieldErrors)?has_content>
			<script>
				var validationErrors = [];
				<#list bindingResult.fieldErrors as fieldError>
					validationErrors['${fieldError.getField()}'] = '${getText(fieldError)}';
				</#list>
			</script>
			</#if>			
			</form>		
			<#if !(RequestParameters.forward)?exists || !RequestParameters.forward?contains("/ajaxforward")>					
			<!--[if !ie]>form end<![endif]-->
			<div id="guide">
				<h2>温馨提示</h2>
				<div class="content">
					<ul>
						<li>请输入您的认证明用户名及密码进行登录；</li>
						<li>如忘记密码,请按提示操作；</li>
						<li>如有疑问进入帮助中心或联系客服；</li>						
					</ul>
				</div>
			</div>
			<!--[if !ie]>guide end<![endif]-->		
			<span class="clr"></span>
			</#if>	
		</div>		
		
	<#if !(RequestParameters.forward)?exists || !RequestParameters.forward?contains("/ajaxforward")>	
		<div id="footer" class="w">
			<div class="flinks">
				<a href="#">关于我们</a>|<a href="#">联系我们</a>|<a href="#">人才招聘</a>|<a href="#">意见反馈</a>|<a href="#">帮助中心</a>
			</div>
			<br/>			
		</div>
	</#if>
	</div>			
		
		
		<!-- 如果统一认证有问题，则隐藏统一认证按钮 -->
		<#if !(RequestParameters.form)?exists && !exception?exists>
			<script type="text/javascript">
				$(function(){
					if(typeof(caslogined) == 'undefined'){
						$('#casLoginBtn').hideme();
					}
				});
			</script>
		</#if>	
	</body>
</html>