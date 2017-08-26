package com.dx.ssh.service;

import java.util.List;
import java.util.Map;

import com.dx.ssh.entity.ArticleEntity;
import com.dx.ssh.types.Page;

public interface ArticleService {
	public void update(ArticleEntity entity);

	public ArticleEntity findById(Integer id);

	public Page<ArticleEntity> queryWithPage(String keywords, String pageNum, String url);

	/**
	 * 获取置顶的文章
	 */
	public List<ArticleEntity> getsTop(int top);

	/**
	 * 获取最新发布文章
	 */
	public List<ArticleEntity> getsLast(int top);

	public void deleteById(Integer id);

	/**
	 * 得到freemarker模版文件所需参数
	 */
	public Map<String, Object> getTemplateParams(int artid, String contextPath, boolean isNew);
}
