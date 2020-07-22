package com.yao.trade.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface VouchRepository extends JpaRepository<VouchEntity, String>, JpaSpecificationExecutor<VouchEntity> {


}
