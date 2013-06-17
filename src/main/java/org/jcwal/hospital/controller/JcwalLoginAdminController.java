/**
 * JcwalLoginAdminController.java 2011-1-14
 */
package org.jcwal.hospital.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.macula.base.security.authentication.CaptchaUsernamePasswordAuthenticationFilter;
import org.macula.base.security.captcha.CaptchaIdGenerator;
import org.macula.base.security.captcha.CaptchaStatus;
import org.macula.base.security.captcha.CaptchaStatusFactory;
import org.macula.base.security.captcha.CaptchaStatusStorage;
import org.macula.base.security.util.SecurityUtils;
import org.macula.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p> <b>JcwalLoginAdminController</b> 登录Controller </p>
 * 
 * @since 2011-1-14
 * @author Rain
 * @version $Id: JcwalLoginAdminController.java 3508 2012-08-22 10:24:12Z wilson $
 */
@Controller
public class JcwalLoginAdminController extends BaseController {

	@Autowired
	private CaptchaIdGenerator captchaIdGenerator;

	@Autowired
	private CaptchaStatusFactory captchaStatusFactory;

	@Autowired
	private CaptchaStatusStorage captchaStatusStorage;

	@Autowired
	private AuthenticationSuccessHandler successHandler;

	@RequestMapping(value = { "/login", "/front/login", "/admin/login" })
	public String login(HttpServletRequest request, Model model, HttpServletResponse response) throws IOException,
			ServletException {
		if (SecurityUtils.isAnonymous()) {
			AuthenticationException exception = getAuthenticationException(request);
			model.addAttribute("exception", exception);
			return showLoginForm(request, model);
		}
		successHandler.onAuthenticationSuccess(request, response, SecurityContextHolder.getContext()
				.getAuthentication());
		return null;
	}

	/* 显示锁屏界面 */
	public String showLockForm(HttpServletRequest request, Model model) {
		return "/admin/jcwal-hcrm-webapp/lock";
	}

	// 显示登录界面
	private String showLoginForm(HttpServletRequest request, Model model) {
		String captchaId = getCaptchaId(request);
		CaptchaStatus captchaStatus = null;
		if (captchaId == null) {
			captchaId = captchaIdGenerator.generateCaptchaId(null);
		}
		captchaStatus = captchaStatusStorage.get(captchaId);
		if (captchaStatus == null) {
			captchaStatus = captchaStatusFactory.getObject();
		}
		captchaStatusStorage.put(captchaId, captchaStatus);

		model.addAttribute("captchaId", captchaId);
		model.addAttribute("captchaStatus", captchaStatus);

		return "/admin/jcwal-hcrm-webapp/login";
	}

	// 检查CAS的用户，完成登录(演示)
	@RequestMapping(value = { "/login_cas_check", "/front/login_cas_check", "/admin/login_cas_check" })
	public void casCheck(HttpServletRequest request, HttpServletResponse response) {
		// 从SESSION中获取Assertion
		final HttpSession session = request.getSession(false);
		final Assertion assertion = session != null ? (Assertion) session
				.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION) : null;
		if (assertion != null) {
			// String userName = assertion.getPrincipal().getName();
			// 根据用户名完成下面的登录
			// 如果本地登录成功写本地SESSION
			// 如果本地登录不成功清除CAS的ASSERTION
			// 跳转到forward指向的地址，没有forward参数则跳转到首页
		}
	}

	private AuthenticationException getAuthenticationException(HttpServletRequest request) {
		return (AuthenticationException) request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public String getCaptchaId(HttpServletRequest request) {
		return (String) request.getAttribute(CaptchaUsernamePasswordAuthenticationFilter.NEW_CAPTCHA_ID);
	}
}
