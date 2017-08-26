package com.dx.ssh.service;

import java.util.List;

import com.dx.ssh.entity.PageViewEntity;

public interface PageViewService {
	public void update(PageViewEntity entity);

	public List<PageViewEntity> findByQuery(String hql);

	public Integer getTotal();

	public List<PageViewEntity> queryWithPage(String hql, int pageNum, int pageSize);

	public Integer getYestodayTotal();
}
