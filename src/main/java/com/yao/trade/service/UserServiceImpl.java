package com.yao.trade.service;

import com.yao.trade.domain.UserEntity;
import com.yao.trade.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public void save(UserEntity userEntity) {
        repository.save(userEntity);
    }

    @Override
    public UserEntity findOne(String id) {
        return repository.findById(id).get();
    }

    @Override
    public UserEntity findByUserNameAndPassword(String userName, String password) {
        return repository.findByUserNameAndPassword(userName,password);
    }
}
