package com.dx.ssh.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.dx.ssh.entity.ArticleCategoryEntity;
import com.dx.ssh.service.ArticleCategoryService;
import com.dx.ssh.service.LogService;
import com.dx.ssh.types.Page;
import com.opensymphony.xwork2.ModelDriven;

public class AdminArticleCategoryAction extends AdminBaseAction
		implements RequestAware, ModelDriven<ArticleCategoryEntity> {
	private static final long serialVersionUID = 1L;
	private ArticleCategoryService articleCategoryService;
	private LogService logService;
	private ArticleCategoryEntity articleCategoryBean;
	private String pagenum;
	private Integer articleCategoryId;
	private String keywords;
	private String method;

	public ArticleCategoryService getArticleCategoryService() {
		return articleCategoryService;
	}

	public void setArticleCategoryService(ArticleCategoryService articleCategoryService) {
		this.articleCategoryService = articleCategoryService;
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

	public Integer getArticleCategoryId() {
		return articleCategoryId;
	}

	public void setArticleCategoryId(Integer articleCategoryId) {
		this.articleCategoryId = articleCategoryId;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String list() {
		loadArticlePager();

		return "list";
	}

	private void loadArticlePager() {
		String url = ServletActionContext.getRequest().getContextPath() + "/AdminArticleCategoryAction_list.action";

		Page<ArticleCategoryEntity> page = getArticleCategoryService().queryWithPage(keywords, getPagenum(), url);
		ServletActionContext.getRequest().setAttribute("page", page);
		ServletActionContext.getRequest().setAttribute("keywords", keywords);

		this.getLogService().createInfo(this.getCurrentUser().getAcount() + "查看了'文章分类列表'页面");
	}

	public String delete() {
		System.out.println("delete log,the log's id is " + this.getArticleCategoryId());

		getArticleCategoryService().deleteById(this.getArticleCategoryId());

		this.getLogService()
				.createInfo(this.getCurrentUser().getAcount() + "删除'文章分类（id=" + this.getArticleCategoryId() + "）'");

		return list();
	}

	public String create() {
		this.method = "Create";
		return "edit";
	}

	public String doCreate() {
		System.out.println("do create...");

		if (this.articleCategoryBean.getTitle() == null || this.articleCategoryBean.getTitle().trim().length() == 0) {
			this.getLogService().createError(
					this.getCurrentUser().getAcount() + "添加'文章分类（name=" + this.articleCategoryBean.getTitle() + "）'失败");
		} else {
			ArticleCategoryEntity articleCategory = new ArticleCategoryEntity();
			articleCategory.setTitle(this.articleCategoryBean.getTitle());
			articleCategory.setRemarks(this.articleCategoryBean.getRemarks());
			articleCategory.setCreateDate(new Timestamp(new Date().getTime()));
			articleCategory.setModifyDate(new Timestamp(new Date().getTime()));
			articleCategory.setRemoveDate(new Timestamp(new Date().getTime()));

			this.getArticleCategoryService().update(articleCategory);

			this.getLogService().createInfo(
					this.getCurrentUser().getAcount() + "添加'文章分类（name=" + this.articleCategoryBean.getTitle() + "）'");
		}

		return "success";
	}

	public String modify() {
		this.method = "Modify";

		this.articleCategoryBean = getArticleCategoryService().findById(this.getArticleCategoryId());
		System.out.println(this.articleCategoryBean);
		request.put("articleCategoryBean", this.articleCategoryBean);

		return "edit";
	}

	public String doModify() {
		System.out.println("do modify...");

		this.getLogService().createInfo(
				this.getCurrentUser().getAcount() + "编辑'文章分类（name=" + this.articleCategoryBean.getTitle() + "）'");

		ArticleCategoryEntity articleCategory = getArticleCategoryService().findById(this.articleCategoryId);
		articleCategory.setTitle(this.articleCategoryBean.getTitle());
		articleCategory.setRemarks(this.articleCategoryBean.getRemarks());
		articleCategory.setModifyDate(new Timestamp(new Date().getTime()));

		this.getArticleCategoryService().update(articleCategory);

		return "success";
	}

	private Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public ArticleCategoryEntity getModel() {
		this.articleCategoryBean = new ArticleCategoryEntity();
		return this.articleCategoryBean;
	}
}
