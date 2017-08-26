package com.dx.ssh.dao;

import java.sql.Timestamp;
import java.util.List;

import com.dx.ssh.entity.ArticleEntity;

public interface ArticleDao extends BaseDao<ArticleEntity> {
	/**
	 * 查next
	 */
	public List<ArticleEntity> findNextArticle(int top, Timestamp createDate);

	/**
	 * 查last
	 */
	public List<ArticleEntity> findLastArticle(int top, Timestamp createDate);

	/**
	 * 最新三篇文章
	 */
	public List<ArticleEntity> findAllLastArticle(int top);
}
