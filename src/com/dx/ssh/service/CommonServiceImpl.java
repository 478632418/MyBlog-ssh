package com.dx.ssh.service;

import java.util.List;

import com.dx.ssh.dao.CommonDao;
import com.dx.ssh.entity.CommonEntity;

public class CommonServiceImpl implements CommonService {
	private CommonDao commonDao;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public List<CommonEntity> getsByArticleId(Integer artcileId) {
		String hql = "FROM CommonEntity l where l.articleId=" + artcileId;

		List<CommonEntity> list = getCommonDao().findByQuery(hql);

		return list;
	}

	@Override
	public List<CommonEntity> getTops(Integer top) {
		String hql = "FROM CommonEntity l limit " + top;

		List<CommonEntity> list = getCommonDao().findByQuery(hql);

		return list;
	}

	@Override
	public void update(CommonEntity entity) {
		this.getCommonDao().update(entity);
	}

}
