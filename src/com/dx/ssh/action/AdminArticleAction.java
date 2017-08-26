package com.dx.ssh.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.dx.ssh.entity.ArticleCategoryEntity;
import com.dx.ssh.entity.ArticleEntity;
import com.dx.ssh.entity.UserEntity;
import com.dx.ssh.service.ArticleCategoryService;
import com.dx.ssh.service.ArticleService;
import com.dx.ssh.service.LogService;
import com.dx.ssh.service.UserService;
import com.dx.ssh.types.Page;
import com.opensymphony.xwork2.ModelDriven;

public class AdminArticleAction extends AdminBaseAction implements RequestAware, ModelDriven<ArticleEntity> {
	private static final long serialVersionUID = 1L;
	private ArticleService articleService;
	private ArticleCategoryService articleCategoryService;
	private UserService userService;
	private LogService logService;
	private ArticleEntity articleBean;
	private String pagenum;
	private String keywords;
	private String method;

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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
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
		String url = ServletActionContext.getRequest().getContextPath() + "/AdminArticleAction_list.action";

		Page<ArticleEntity> page = this.getArticleService().queryWithPage(keywords, getPagenum(), url);
		List<ArticleCategoryEntity> categories = this.getArticleCategoryService().findAll();
		Map<Integer, String> categroyIdVsName = new HashMap<>();
		for (ArticleCategoryEntity category : categories) {
			categroyIdVsName.put(category.getId(), category.getTitle());
		}

		// List<UserEntity> users= this.getUserService().getUserList();
		// Map<Integer, String> userIdVsName = new HashMap<>();
		// for (UserEntity user : users) {
		// userIdVsName.put(user.getId(), user.getAcount());
		// }

		ServletActionContext.getRequest().setAttribute("categroyIdVsName", categroyIdVsName);
		// ServletActionContext.getRequest().setAttribute("userIdVsName",
		// userIdVsName);
		ServletActionContext.getRequest().setAttribute("page", page);
		ServletActionContext.getRequest().setAttribute("keywords", keywords);

		this.getLogService().createInfo(this.getCurrentUser().getAcount() + "查看了'文章分类列表'页面");
	}

	public String delete() {
		System.out.println("delete log,the log's id is " + this.articleBean.getId());

		this.getArticleService().deleteById(this.articleBean.getId());

		this.getLogService()
				.createInfo(this.getCurrentUser().getAcount() + "删除'文章（id=" + this.articleBean.getId() + "）'");

		return list();
	}

	public String create() {
		this.method = "Create";

		List<ArticleCategoryEntity> categories = this.getArticleCategoryService().findAll();
		ServletActionContext.getRequest().setAttribute("categories", categories);

		return "edit";
	}

	public String doCreate() {
		System.out.println("do create...");

		if (this.articleBean.getTitle() == null || this.articleBean.getTitle().trim().length() == 0
				|| this.articleBean.getCategoryId() == 0) {
			this.getLogService().createError(
					this.getCurrentUser().getAcount() + "添加'文章（title=" + this.articleBean.getTitle() + "）'失败");
		} else {
			ArticleEntity article = new ArticleEntity();
			article.setId(0);
			article.setTitle(this.articleBean.getTitle());
			article.setAuthor(this.getCurrentUser().getName());
			article.setContent(this.articleBean.getContent());
			article.setMeta(this.articleBean.getMeta());
			article.setRemark(this.articleBean.getRemark());
			article.setType(this.articleBean.getType());
			article.setCategoryId(this.articleBean.getCategoryId());
			article.setLikes(this.articleBean.getLikes());
			article.setLooks(this.articleBean.getLooks());
			article.setTop(this.articleBean.getTop());
			article.setType(this.articleBean.getType());
			article.setUserId(this.getCurrentUser().getId());
			//article.setStaticUrl(this.articleBean.staticPath());
			article.setCreateDate(new Timestamp(new Date().getTime()));

			this.getArticleService().update(article);

			this.getLogService().createInfo(
					this.getCurrentUser().getAcount() + "添加'文章（title=" + this.articleBean.getTitle() + "）'");
		}

		return "success";
	}

	public String modify() {
		this.method = "Modify";

		List<ArticleCategoryEntity> categories = this.getArticleCategoryService().findAll();
		ServletActionContext.getRequest().setAttribute("categories", categories);

		this.articleBean = getArticleService().findById(this.articleBean.getId());
		System.out.println(this.articleBean);
		ServletActionContext.getRequest().setAttribute("articleBean", this.articleBean);

		return "edit";
	}

	public String doModify() {
		System.out.println("do modify...");
		if (this.articleBean.getTitle() == null || this.articleBean.getTitle().trim().length() == 0
				|| this.articleBean.getCategoryId() == 0 || this.articleBean.getId() == 0) {
			this.getLogService().createError(
					this.getCurrentUser().getAcount() + "添加'文章（title=" + this.articleBean.getTitle() + "）'失败");
		} else {
			this.getLogService().createInfo(
					this.getCurrentUser().getAcount() + "编辑'文章（title=" + this.articleBean.getTitle() + "）'");

			ArticleEntity article = getArticleService().findById(this.articleBean.getId());
			article.setTitle(this.articleBean.getTitle());
			article.setContent(this.articleBean.getContent());
			article.setMeta(this.articleBean.getMeta());
			article.setRemark(this.articleBean.getRemark());
			article.setType(this.articleBean.getType());
			article.setCategoryId(this.articleBean.getCategoryId());
			article.setLikes(this.articleBean.getLikes());
			article.setLooks(this.articleBean.getLooks());
			article.setTop(this.articleBean.getTop());
			article.setType(this.articleBean.getType());

			article.setModifyDate(new Timestamp(new Date().getTime()));

			this.getArticleService().update(article);
		}
		return "success";
	}

	@Override
	public ArticleEntity getModel() {
		this.articleBean = new ArticleEntity();
		return this.articleBean;
	}

	private Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = request;
	}

}
