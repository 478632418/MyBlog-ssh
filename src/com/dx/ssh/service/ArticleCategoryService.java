package com.dx.ssh.service;

import java.util.List;

import com.dx.ssh.entity.ArticleCategoryEntity;
import com.dx.ssh.types.Page;

public interface ArticleCategoryService {
	public void update(ArticleCategoryEntity entity);

	public ArticleCategoryEntity findById(Integer id);
	
	public Page<ArticleCategoryEntity> queryWithPage(String keywords, String pageNum, String url);

	public void deleteById(Integer id);

	public List<ArticleCategoryEntity> findAll();

}
