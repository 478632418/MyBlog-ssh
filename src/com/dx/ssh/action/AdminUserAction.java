package com.dx.ssh.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.dx.ssh.entity.UserEntity;
import com.dx.ssh.service.LogService;
import com.dx.ssh.service.UserService;
import com.dx.ssh.settings.ConstsSettings;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends AdminBaseAction implements RequestAware, ModelDriven<UserEntity> {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private LogService logService;
	private UserEntity userBean;
	private String pagenum;
	private String method;
	private File myFile;
	private String myFileContentType;
	private String myFileFileName;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public String getPagenum() {
		return pagenum;
	}

	public void setPagenum(String pagenum) {
		this.pagenum = pagenum;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public String edit() {
		UserEntity user = (UserEntity) ActionContext.getContext().getSession().get(ConstsSettings.ADMIN_SESSION_KEY);

		if (user == null) {
			request.put("onlineuser", user);
			return "admin";
		}

		UserEntity persistUser = getUserService().findById(user.getId());
		request.put("onlineuser", persistUser);
		
		return "edit";
	}

	public String doEdit() {
		UserEntity user = (UserEntity) ActionContext.getContext().getSession().get(ConstsSettings.ADMIN_SESSION_KEY);
		UserEntity persistUser = getUserService().findById(user.getId());

		if (userBean.getName() != null && userBean.getName().trim().length() > 0) {
			persistUser.setName(userBean.getName());
		}
		if (userBean.getEmail() != null && userBean.getEmail().trim().length() > 0) {
			persistUser.setEmail(userBean.getEmail());
		}
		if (userBean.getPhone() != null && userBean.getPhone().trim().length() > 0) {
			persistUser.setPhone(userBean.getPhone());
		}
		if (userBean.getQq() != null && userBean.getQq().trim().length() > 0) {
			persistUser.setQq(userBean.getQq());
		}
		if (userBean.getWeibo() != null && userBean.getWeibo().trim().length() > 0) {
			persistUser.setWeibo(userBean.getWeibo());
		}
		if (userBean.getIntro() != null && userBean.getIntro().trim().length() > 0) {
			persistUser.setIntro(userBean.getIntro());
		}
		persistUser.setModifyDate(new Timestamp(new Date().getTime()));

		getUserService().update(persistUser);
		persistUser = getUserService().findById(user.getId());

		request.put("onlineuser", persistUser);
		ActionContext.getContext().getSession().put(ConstsSettings.ADMIN_SESSION_KEY, persistUser);

		getLogService().createInfo("修改个人信息成功。");

		return "edit";
	}

	public String doEditAvatar() throws Exception {
		UserEntity user = (UserEntity) ActionContext.getContext().getSession().get(ConstsSettings.ADMIN_SESSION_KEY);
		if (myFile == null || (!this.myFileFileName.endsWith(".jpg") && !this.myFileFileName.endsWith(".png")
				&& !this.myFileFileName.endsWith(".gif") && !this.myFileFileName.endsWith(".bmp"))) {
			request.put("onlineuser", user);
			return "edit";
		}

		ServletContext servletContext = ServletActionContext.getServletContext();
		String avatarSavePath = "/files/avatar/" + myFileFileName;
		String savePath = servletContext.getRealPath(avatarSavePath);
		System.out.println(savePath);

		FileOutputStream out = new FileOutputStream(savePath);
		FileInputStream in = new FileInputStream(myFile);

		byte[] bytes = new byte[1024];
		int length = 0;

		while ((length = in.read(bytes)) != -1) {
			out.write(bytes, 0, length);
		}

		out.close();
		in.close();

		UserEntity persistUser = getUserService().findById(user.getId());

		persistUser.setAvatar(avatarSavePath.substring(1));
		persistUser.setModifyDate(new Timestamp(new Date().getTime()));

		getUserService().update(persistUser);
		persistUser = getUserService().findById(user.getId());

		request.put("onlineuser", persistUser);
		ActionContext.getContext().getSession().put(ConstsSettings.ADMIN_SESSION_KEY, persistUser);

		getLogService().createInfo("修改个人头像成功。");

		return "edit";
	}

	private Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public UserEntity getModel() {
		this.userBean = new UserEntity();
		return this.userBean;
	}
}
