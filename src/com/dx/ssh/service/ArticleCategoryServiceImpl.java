package com.dx.ssh.service;

import java.util.List;

import com.dx.ssh.dao.ArticleCategoryDao;
import com.dx.ssh.entity.ArticleCategoryEntity;
import com.dx.ssh.types.Page;
import com.dx.ssh.utils.StringUtils;

public class ArticleCategoryServiceImpl implements ArticleCategoryService {
	private ArticleCategoryDao articleCategoryDao;

	public ArticleCategoryDao getArticleCategoryDao() {
		return articleCategoryDao;
	}

	public void setArticleCategoryDao(ArticleCategoryDao articleCategoryDao) {
		this.articleCategoryDao = articleCategoryDao;
	}

	@Override
	public void update(ArticleCategoryEntity entity) {
		getArticleCategoryDao().update(entity);
	}

	@Override
	public ArticleCategoryEntity findById(Integer id) {
		return getArticleCategoryDao().findById(id);
	}

	@Override
	public Page<ArticleCategoryEntity> queryWithPage(String keywords, String pageNum, String url) {
		String hqlTotal = "Select Count(0) FROM ArticleCategoryEntity l ";
		if (!StringUtils.isBlank(keywords)) {
			hqlTotal = hqlTotal + "where l.title like '" + keywords + "%' ";
		}
		// 总记录数
		Integer totalrecord = getArticleCategoryDao().getTotal(hqlTotal);
		Page<ArticleCategoryEntity> page = null;
		if (pageNum == null) { // 没传递页号，回传第一页数据
			page = new Page<ArticleCategoryEntity>(totalrecord, 1);
		} else { // 根据传递的页号查找所需显示数据
			page = new Page<ArticleCategoryEntity>(totalrecord, Integer.parseInt(pageNum));
		}

		String hql = "FROM ArticleCategoryEntity l ";
		if (!StringUtils.isBlank(keywords)) {
			hql = hql + " where l.title like '" + keywords + "%' ";
		}
		hql += "order by l.id desc ";

		List<ArticleCategoryEntity> list = getArticleCategoryDao().queryWithPage(hql, page.getPagenum(),
				page.getPagesize());
		page.setList(list);
		page.setUrl(url);

		return page;
	}

	@Override
	public void deleteById(Integer id) {
		ArticleCategoryEntity entity = getArticleCategoryDao().findById(id);
		if (entity != null) {
			getArticleCategoryDao().delete(entity);
		}
	}

	@Override
	public List<ArticleCategoryEntity> findAll() {		
		return getArticleCategoryDao().findAll();
	}

}
