package com.yao.trade.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeedRepository extends JpaRepository<SeedEntity, String>, JpaSpecificationExecutor<SeedEntity> {


    @Query(value = "select * from t_seed t where t.ip = ?1 and t.created_time > DATE_SUB(NOW(), INTERVAL 5 MINUTE) ",nativeQuery = true)
    List<SeedEntity> findByIp(String realIpAddress);
}
