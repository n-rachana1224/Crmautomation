package com.crm.automation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.automation.entity.SalesOrderEntity;

public interface SalesOrderRepository extends JpaRepository<SalesOrderEntity ,Long> {

}
