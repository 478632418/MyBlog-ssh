package com.dx.ssh.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dx.ssh.dao.ArticleCategoryDao;
import com.dx.ssh.dao.ArticleDao;
import com.dx.ssh.entity.ArticleCategoryEntity;
import com.dx.ssh.entity.ArticleEntity;
import com.dx.ssh.types.Page;
import com.dx.ssh.utils.StringUtils;

public class ArticleServiceImpl implements ArticleService {
	private ArticleDao articleDao;
	private ArticleCategoryDao articleCategoryDao;

	public ArticleDao getArticleDao() {
		return articleDao;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public ArticleCategoryDao getArticleCategoryDao() {
		return articleCategoryDao;
	}

	public void setArticleCategoryDao(ArticleCategoryDao articleCategoryDao) {
		this.articleCategoryDao = articleCategoryDao;
	}

	@Override
	public void update(ArticleEntity entity) {
		getArticleDao().update(entity);
	}

	@Override
	public ArticleEntity findById(Integer id) {
		return getArticleDao().findById(id);
	}

	@Override
	public Page<ArticleEntity> queryWithPage(String keywords, String pageNum, String url) {
		String hqlTotal = "Select Count(0) FROM ArticleEntity l ";
		if (!StringUtils.isBlank(keywords)) {
			hqlTotal = hqlTotal + "where l.title like '" + keywords + "%' ";
		}
		// 总记录数
		Integer totalrecord = getArticleDao().getTotal(hqlTotal);
		Page<ArticleEntity> page = null;
		if (pageNum == null) { // 没传递页号，回传第一页数据
			page = new Page<ArticleEntity>(totalrecord, 1);
		} else { // 根据传递的页号查找所需显示数据
			page = new Page<ArticleEntity>(totalrecord, Integer.parseInt(pageNum));
		}

		String hql = "FROM ArticleEntity l ";
		if (!StringUtils.isBlank(keywords)) {
			hql = hql + " where l.title like '" + keywords + "%' ";
		}
		hql += "order by l.id desc ";

		List<ArticleEntity> list = getArticleDao().queryWithPage(hql, page.getPagenum(), page.getPagesize());
		page.setList(list);
		page.setUrl(url);

		return page;
	}

	@Override
	public void deleteById(Integer id) {
		ArticleEntity entity = getArticleDao().findById(id);
		if (entity != null) {
			getArticleDao().delete(entity);
		}
	}

	@Override
	public List<ArticleEntity> getsTop(int top) {
		String hql = "From ArticleEntity e Where e.top=1 order by e.id desc";
		return getArticleDao().getTops(hql, top);
	}

	@Override
	public List<ArticleEntity> getsLast(int top) {
		String hql = "From ArticleEntity e order by e.createDate desc";
		return getArticleDao().getTops(hql, top);
	}

	/**
	 * 得到freemarker模版文件所需参数
	 */
	@Override
	public Map<String, Object> getTemplateParams(int articleId, String contextPath, boolean isNew) {
		System.out.println("articleId=" + articleId);
		// 要看的文章
		ArticleEntity article = getArticleDao().findById(articleId);

		if (article == null || article.getId() <= 0)
			return null;

		// 最新三篇文章
		List<ArticleEntity> lastArticles = getArticleDao().findAllLastArticle(3);

		// 所有类别
		List<ArticleCategoryEntity> categories = getArticleCategoryDao().findAll();

		// 下一篇
		ArticleEntity next = null;
		List<ArticleEntity> nextArticles = articleDao.findNextArticle(3, article.getCreateDate());
		if (nextArticles == null || nextArticles.size() <= 0) {
			next = new ArticleEntity();
			next.setStaticUrl("#");
			next.setTitle("这是最后一篇了哦！");
		} else {
			next = nextArticles.get(0);
			next.setStaticUrl(contextPath + next.getStaticUrl() + ".html");
		}

		// 上一篇文章
		ArticleEntity last = null;
		List<ArticleEntity> lastAs = articleDao.findLastArticle(3, article.getCreateDate());
		if (lastAs == null || lastAs.size() <= 0) {
			last = new ArticleEntity();
			last.setStaticUrl("#");
			last.setTitle("这是第一篇哦！");
		} else {
			last = lastAs.get(0);
			last.setStaticUrl(contextPath + last.getStaticUrl() + ".html");
		}

		java.text.SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 封装模版所需参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("looked", article.getLooks());
		params.put("articleId", article.getId());
		params.put("time", format.format(article.getCreateDate()));
		params.put("title", article.getTitle());
		params.put("content", article.getContent());
		params.put("typeString", "------------------------------------------------------");
		params.put("author", article.getAuthor());
		params.put("categoryName", getArticleCategoryDao().findById(article.getCategoryId()).getTitle());
		params.put("likes", article.getLikes());
		params.put("listCategoryArticle", contextPath + "/listArticle.action?categoryId=" + article.getCategoryId());
		params.put("lastArticlesList", lastArticles);
		params.put("categoryList", categories);
		params.put("likesURL", contextPath + "/likeAction.action?articleId=" + article.getId());
		params.put("nextArticle", next);
		params.put("lastArticle", last);
		params.put("staticURL", article.getStaticUrl());

		return params;
	}

}
