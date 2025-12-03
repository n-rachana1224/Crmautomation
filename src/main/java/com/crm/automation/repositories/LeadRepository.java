package com.crm.automation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.automation.entity.LeadEntity;

public interface LeadRepository extends JpaRepository<LeadEntity,Long> {

}
