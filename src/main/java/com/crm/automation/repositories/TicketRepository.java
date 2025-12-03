package com.crm.automation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.automation.entity.TicketEntity;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity,Long> {

}
