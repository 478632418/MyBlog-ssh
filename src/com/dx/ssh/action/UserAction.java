package com.dx.ssh.action;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.dx.ssh.entity.UserEntity;
import com.dx.ssh.listener.SessionListener;
import com.dx.ssh.service.UserService;
import com.dx.ssh.settings.ConstsSettings;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;

public class UserAction extends ActionSupport implements RequestAware {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private UserEntity userBean;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserEntity getUserBean() {
		return userBean;
	}

	public void setUserBean(UserEntity userBean) {
		this.userBean = userBean;
	}

	public String login() {
		return "login";
	}

	/**
	 * login action
	 */
	public String doLogin() {
		System.out.println("do login");
		String acount = this.getUserBean().getAcount();
		String pwd = this.getUserBean().getPwd();
		System.out.println("acount=" + acount + ",pwd=" + pwd);

		UserEntity user = getUserService().login(acount, pwd);
		if (user != null) {
			System.out.println(user);
			ActionContext.getContext().getSession().put(ConstsSettings.ADMIN_SESSION_KEY, user);

			return SUCCESS;
		}

		return ERROR;
	}

	public String register() {
		return "register";
	}

	public String doRegister() {
		String acount = this.getUserBean().getAcount();
		String pwd = this.getUserBean().getPwd();
		String email = this.getUserBean().getEmail();
		String phone = this.getUserBean().getPhone();

		if (acount == null || acount.trim().equals("") || pwd == null || pwd.trim().equals("") || email == null
				|| email.trim().equals("") || phone == null || phone.trim().equals("")) {
			return "register_error";
		}

		Timestamp now = new Timestamp(new Date().getTime());

		UserEntity user = new UserEntity();
		user.setAcount(acount);
		user.setPwd(pwd);
		user.setEmail(email);
		user.setPhone(phone);
		user.setCreateDate(now);

		int i = getUserService().register(user);

		System.out.println("i=" + i);
		if (i == 1) {
			return "register_success";
		}
		return "register_error";
	}

	public String logout() {
		ActionContext.getContext().getSession().remove(ConstsSettings.ADMIN_SESSION_KEY);

		return ERROR;
	}

	private Map<String, Object> request = null;

	public Map<String, Object> getRequest() {
		return request;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;

	}
}
