package com.dx.ssh.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dx.ssh.dao.LogDao;
import com.dx.ssh.entity.LogEntity;
import com.dx.ssh.entity.UserEntity;
import com.dx.ssh.settings.ConstsSettings;
import com.dx.ssh.types.Page;
import com.dx.ssh.utils.AddressUtils;
import com.dx.ssh.utils.StringUtils;
import com.opensymphony.xwork2.ActionContext;

public class LogServiceImpl implements LogService {
	private LogDao logDao;

	public LogDao getLogDao() {
		return logDao;
	}

	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

	@Override
	public List<LogEntity> findByQuery(String hql) {
		return getLogDao().findByQuery(hql);
	}

	@Override
	public Page<LogEntity> queryWithPage(String logType, String pageNum, String url) {
		System.out.println("页号" + pageNum);

		String hqlTotal = "Select Count(0) FROM LogEntity l";
		if (!StringUtils.isBlank(logType)) {
			hqlTotal = hqlTotal + " where l.type='" + logType + "'";
		}
		// 总记录数
		Integer totalrecord = getLogDao().getTotal(hqlTotal);
		Page<LogEntity> page = null;
		if (pageNum == null) { // 没传递页号，回传第一页数据
			page = new Page<LogEntity>(totalrecord, 1);
		} else { // 根据传递的页号查找所需显示数据
			page = new Page<LogEntity>(totalrecord, Integer.parseInt(pageNum));
		}

		String hql = "FROM LogEntity l ";
		if (!StringUtils.isBlank(logType)) {
			hql = hql + " where l.type='" + logType + "' ";
		}
		hql += "order by l.createDate desc ";
		
		List<LogEntity> list = getLogDao().queryWithPage(hql, page.getPagenum(), page.getPagesize());
		page.setList(list);
		page.setUrl(url);

		return page;
	}

	@Override
	public void createWarn(String msg) {
		create("warn", msg);
	}

	@Override
	public void createInfo(String msg) {
		create("info", msg);
	}

	@Override
	public void createError(String msg) {
		create("error", msg);
	}

	private void create(String type, String msg) {
		LogEntity entity = new LogEntity();

		entity.setType(type);
		entity.setMsg(msg);
		try {
			String ip = AddressUtils.getIpAddr(ServletActionContext.getRequest());
			entity.setIp(ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserEntity user = (UserEntity) ActionContext.getContext().getSession().get(ConstsSettings.ADMIN_SESSION_KEY);
		if (user != null) {
			entity.setAccount(user.getAcount());
		}
		entity.setCreateDate(new Timestamp(new Date().getTime()));

		getLogDao().update(entity);
	}

	@Override
	public void deleteById(Integer logId) {
		LogEntity logEntity = getLogDao().findById(logId);
		if (logEntity != null) {
			getLogDao().delete(logEntity);
		}
	}
}
