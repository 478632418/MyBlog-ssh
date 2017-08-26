package com.dx.ssh.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.dx.ssh.dao.UserDao;
import com.dx.ssh.dao.UserDaoImpl;
import com.dx.ssh.entity.UserEntity;
import com.dx.ssh.types.Page;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Override
	public int register(UserEntity entity) {
		String acount = entity.getAcount();
		System.out.println("acount=" + acount);
		if (acount.indexOf("'") != -1) {
			return -1;
		}
		List<UserEntity> list = getUserDao().findByQuery("from UserEntity where acount='" + acount + "'");
		if (list.isEmpty()) {
			getUserDao().update(entity);
			System.out.println("自动生成的主键ID" + entity.getId());
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public void delete(UserEntity entity) {
		getUserDao().delete(entity);
	}

	@Override
	public void update(UserEntity entity) {
		getUserDao().update(entity);
	}

	@Override
	public UserEntity login(String acount, String pwd) {
		return getUserDao().login(acount, pwd);
	}

	@Override
	public int updateByQuery(String hql) {
		return getUserDao().updateByQuery(hql);
	}

	@Override
	public UserEntity findById(Integer id) {
		return getUserDao().findById(id);
	}

	@Override
	public List<UserEntity> findByQuery(String hql) {
		return getUserDao().findByQuery(hql);
	}

	@Override
	public List<UserEntity> getUserList() {
		return getUserDao().findAll();
	}

	@Override
	public List<UserEntity> queryWithPage(String hql, int pageNum, int pageSize) {
		return getUserDao().queryWithPage(hql, pageNum, pageSize);
	}

	@Override
	public void createTestAcount() {
		Timestamp now = new Timestamp(new Date().getTime());

		UserEntity waitingInserUser = new UserEntity();
		waitingInserUser.setAcount("test");
		waitingInserUser.setCreateDate(now);
		waitingInserUser.setEmail("test@dx.com");
		waitingInserUser.setModifyDate(now);
		waitingInserUser.setName("测试账户");
		waitingInserUser.setPhone("18518379888");
		waitingInserUser.setPwd("123456");
		waitingInserUser.setRemark("测试账户。。。");
		waitingInserUser.setRemoveDate(null);
		waitingInserUser.setSex("男");
		waitingInserUser.setState("激活");
		waitingInserUser.setType("管理员");
		getUserDao().update(waitingInserUser);
	}

	@Override
	public Page<UserEntity> queryWithPage(String pagenum, String url) {
		System.out.println("页号" + pagenum);
		// 总记录数
		int totalrecord = (int) getUserDao().getTotal();
		Page<UserEntity> page = null;
		if (pagenum == null)
			// 没传递页号，回传第一页数据
			page = new Page<UserEntity>(totalrecord, 1);
		else
			// 根据传递的页号查找所需显示数据
			page = new Page<UserEntity>(totalrecord, Integer.parseInt(pagenum));
		System.out.println("page.getStartindex()" + page.getStartindex());
		List<UserEntity> list = getUserDao().queryWithPage("From UserEntity  order by Id desc", page.getStartindex(),
				page.getPagesize());
		page.setList(list);
		page.setUrl(url);
		return page;
	}
}
