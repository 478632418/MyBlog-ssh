package com.dx.ssh.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.dx.ssh.entity.ArticleCategoryEntity;
import com.dx.ssh.entity.ArticleEntity;
import com.dx.ssh.service.ArticleCategoryService;
import com.dx.ssh.service.ArticleService;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private ArticleService articleService;
	private ArticleCategoryService articleCategoryService;

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public ArticleCategoryService getArticleCategoryService() {
		return articleCategoryService;
	}

	public void setArticleCategoryService(ArticleCategoryService articleCategoryService) {
		this.articleCategoryService = articleCategoryService;
	}

	public String index() {
		List<ArticleEntity> topArticles = this.getArticleService().getsTop(6);
		List<ArticleEntity> lastArticlesList = this.getArticleService().getsLast(6);
		List<ArticleCategoryEntity> categories = this.getArticleCategoryService().findAll();
		Map<Integer, String> categroyIdVsName = new HashMap<>();
		for (ArticleCategoryEntity category : categories) {
			categroyIdVsName.put(category.getId(), category.getTitle());
		}
		
		Map<String, Object> params = new HashMap<>();
		params.put("topArticles", topArticles);
		params.put("lastArticlesList", lastArticlesList);
		params.put("categories", categories);
		params.put("categroyIdVsName", categroyIdVsName);
		ServletActionContext.getRequest().setAttribute("params", params);
		return SUCCESS;
	}
}
