package com.dx.ssh.interceptor;

import java.util.Map;

import com.dx.ssh.action.UserAction;
import com.dx.ssh.entity.UserEntity;
import com.dx.ssh.settings.ConstsSettings;
import com.dx.ssh.utils.ConfigUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	public static final String LOGIN_PAGE = "login";

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		System.out.println("进入拦截器");

		// 检查是否是登录页面的Action
		if (actionInvocation.getAction() instanceof UserAction) {
			// 判断是否是登录相关的Method
			if (actionInvocation.getProxy().getMethod().equals("login")
					|| actionInvocation.getProxy().getMethod().equals("doLogin"))
				return actionInvocation.invoke();
			// 判断注册功能是否开启，并且执行Method是否是注册相关方法
			else if (ConfigUtils.getProperty("register").equals("true")
					&& (actionInvocation.getProxy().getMethod().equals("register")
							|| actionInvocation.getProxy().getMethod().equals("doRegister"))) {
				return actionInvocation.invoke();
			} else
				return LOGIN_PAGE;
		}

		Map session = actionInvocation.getInvocationContext().getSession();
		// 检查是否已登录
		UserEntity user = (UserEntity) session.get(ConstsSettings.ADMIN_SESSION_KEY);

		if (user == null) { // 未登录，跳转到登录页面
			return LOGIN_PAGE;
		} else { // 已登录，继续执行
			return actionInvocation.invoke();
		}
	}
}
