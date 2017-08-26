package com.dx.ssh.service;

import java.util.List;

import com.dx.ssh.entity.LogEntity;
import com.dx.ssh.types.Page;

public interface LogService {
	public void createWarn(String msg);

	public void createInfo(String msg);

	public void createError(String msg);

	public List<LogEntity> findByQuery(String hql);

	public Page<LogEntity> queryWithPage(String logType, String pageNum, String url);

	public void deleteById(Integer logId);
}
