package com.crm.automation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.automation.entity.CompanyEntity;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity,Long> {

}
