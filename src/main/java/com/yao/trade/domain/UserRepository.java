package com.yao.trade.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {


    @Query("select u from UserEntity  u where u.userName = ?1 and u.password = ?2")
    UserEntity findByUserNameAndPassword(String userName, String password);


    @Query("select u from UserEntity  u where u.userName = ?1")
    UserEntity findByUserName(String userName);
}
