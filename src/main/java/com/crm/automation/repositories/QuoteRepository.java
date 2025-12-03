package com.crm.automation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.automation.entity.QuoteEntity;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity,Long> {

}
