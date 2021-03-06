package com.yao.trade.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import java.util.List;

public interface SeedRepository extends JpaRepository<SeedEntity, String>, JpaSpecificationExecutor<SeedEntity> {


    @Query(value = "select * from t_seed t where t.ip = ?1 and t.created_time > DATE_SUB(NOW(), INTERVAL 10 SECOND ) ",nativeQuery = true)
    List<SeedEntity> findByIp(String realIpAddress);

    @Modifying
    @Query("delete from SeedEntity s where s.id = ?2 and s.ip = ?1")
    void deleteByIpAndId(String realIpAddress, String id);

    @Query("select s from SeedEntity s where s.id = ?2 and s.ip = ?1")
    SeedEntity findByIpAndId(String realIpAddress, String id);

    @Query("delete  from SeedEntity  s where  s.roleName = ?1 and s.operateNumber = ?2 and s.mul = true ")
    @Modifying
    void deleteMulByRoleNameAndOperateNumber(String roleName, String operateNumber);

    @Query("delete  from SeedEntity  s where s.userId = ?1 and s.mul = true ")
    @Modifying
    void deleteByUserId(String userId);

    @Query("select  s from SeedEntity s where s.id = ?1")
    SeedEntity findOne(String id);
}
