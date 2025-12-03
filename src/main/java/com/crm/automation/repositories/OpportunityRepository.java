package com.crm.automation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.automation.entity.OpportunityEntity;

@Repository
public interface OpportunityRepository extends JpaRepository<OpportunityEntity,Long> {

}
