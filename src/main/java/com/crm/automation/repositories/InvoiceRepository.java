package com.crm.automation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.automation.entity.InvoiceEntity;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

}
