package com.dx.ssh.service;

import java.util.List;

import com.dx.ssh.entity.UserEntity;
import com.dx.ssh.types.Page;

public interface UserService {
	public void createTestAcount();

	public int register(UserEntity entity);

	public void delete(UserEntity entity);

	public void update(UserEntity entity);

	public UserEntity login(String acount, String pwd);

	public int updateByQuery(String hql);

	public UserEntity findById(Integer id);

	public List<UserEntity> findByQuery(String hql);

	public List<UserEntity> getUserList();

	public List<UserEntity> queryWithPage(String hql, int pageNum, int pageSize);

	public Page<UserEntity> queryWithPage(String pagenum, String url);

}
