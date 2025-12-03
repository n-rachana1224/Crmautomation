package com.crm.automation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.automation.entity.ContactEntity;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity,Long> {

}
