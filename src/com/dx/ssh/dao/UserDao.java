package com.dx.ssh.dao;

import com.dx.ssh.entity.UserEntity;

public interface UserDao extends BaseDao<UserEntity>{
    public UserEntity login(String uAcount, String uPwd);

}
