package com.dx.ssh.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.dx.ssh.dao.PageViewDao;
import com.dx.ssh.entity.PageViewEntity;

public class PageViewServiceImpl implements PageViewService {
	private PageViewDao pageViewDao;

	public PageViewDao getPageViewDao() {
		return pageViewDao;
	}

	public void setPageViewDao(PageViewDao pageViewDao) {
		this.pageViewDao = pageViewDao;
	}

	@Override
	public void update(PageViewEntity entity) {
		getPageViewDao().update(entity);
	}

	@Override
	public List<PageViewEntity> findByQuery(String hql) {
		return getPageViewDao().findByQuery(hql);
	}

	@Override
	public Integer getTotal() {
		return getPageViewDao().getTotal();
	}

	@Override
	public List<PageViewEntity> queryWithPage(String hql, int pageNum, int pageSize) {
		return getPageViewDao().queryWithPage(hql, pageNum, pageSize);
	}

	@Override
	public Integer getYestodayTotal() {
		Integer count = 0;
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(endDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		
		try {
			Date beginDate = dft.parse(dft.format(date.getTime()));
			String begin = dft.format(date.getTime());
			String end = dft.format(endDate);
			count = getPageViewDao()
					.findByQuery("From PageViewEntity p Where p.createDate>='" + begin + "' and p.createDate<'" + end + "'")
					.size();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return count;
	}

}
