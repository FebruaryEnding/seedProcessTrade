package com.yao.trade.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SeedRepository extends JpaRepository<SeedEntity, String>, JpaSpecificationExecutor<SeedEntity> {


}
