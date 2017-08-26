package com.dx.ssh.action;

import java.util.Map;

import com.dx.ssh.entity.UserEntity;
import com.dx.ssh.settings.ConstsSettings;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminBaseAction extends ActionSupport {
	/**
	 * 获取当前登录后台的用户信息
	 */
	protected UserEntity getCurrentUser() {
		Map session = ActionContext.getContext().getSession();
		// 检查是否已登录
		UserEntity user = (UserEntity) session.get(ConstsSettings.ADMIN_SESSION_KEY);
		return user;
	}

}
