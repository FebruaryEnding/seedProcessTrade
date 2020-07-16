package com.yao.trade.service;

import com.yao.trade.domain.UserEntity;

public interface IUserService {

    void save(UserEntity userEntity);

    UserEntity findOne(String id);

    UserEntity findByUserNameAndPassword(String userName, String password);
}
