package com.dx.ssh.action;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.aspectj.weaver.ast.Var;

import com.dx.ssh.entity.LogEntity;
import com.dx.ssh.service.LogService;
import com.dx.ssh.types.Page;

public class AdminLogAction extends AdminBaseAction {
	private static final long serialVersionUID = 1L;
	private LogService logService;
	private String pagenum;
	private Integer logId;

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

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	private Map<Integer, String> logTypes = new LinkedHashMap<Integer, String>();
	private Integer logType = null;

	public Map<Integer, String> getLogTypes() {
		return logTypes;
	}

	public void setLogTypes(Map<Integer, String> logTypes) {
		this.logTypes = logTypes;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public AdminLogAction() {
		logTypes.put(1, "info");
		logTypes.put(2, "warn");
		logTypes.put(3, "error");
	}

	public String list() {
		// 分页查询所有类型
		String url = ServletActionContext.getRequest().getContextPath() + "/AdminLogAction_list.action";
		String logTypeStr = "";

		if (getLogType() != null && getLogTypes().containsKey(getLogType())) {
			logTypeStr = getLogTypes().get(getLogType());
		}

		Page<LogEntity> page = getLogService().queryWithPage(logTypeStr, getPagenum(), url);
		ServletActionContext.getRequest().setAttribute("page", page);
		for (LogEntity log : page.getList()) {
			System.out.println(log);
		}
		ServletActionContext.getRequest().setAttribute("logTypes", logTypes);
		ServletActionContext.getRequest().setAttribute("logType", logType);

		return "list";
	}

	public String delete() {
		System.out.println("delete log,the log's id is " + getLogId());

		getLogService().deleteById(getLogId());

		String url = ServletActionContext.getRequest().getContextPath() + "/AdminLogAction_list.action";
		String logTypeStr = "";

		if (getLogType() != null && getLogTypes().containsKey(getLogType())) {
			logTypeStr = getLogTypes().get(getLogType());
		}

		Page<LogEntity> page = getLogService().queryWithPage(logTypeStr, getPagenum(), url);
		System.out.println(page);
		ServletActionContext.getRequest().setAttribute("page", page);

		return "list";
	}
}
