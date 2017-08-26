package com.dx.ssh.service;

import java.util.List;

import com.dx.ssh.entity.CommonEntity;

public interface CommonService {
	public List<CommonEntity> getsByArticleId(Integer artcileId);

	public List<CommonEntity> getTops(Integer top);

	public void update(CommonEntity entity);
}
