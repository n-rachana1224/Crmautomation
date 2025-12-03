package com.crm.automation.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contactName;
    private String email;
    private String phone;
    private String designation;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    private LocalDateTime createdAt = LocalDateTime.now();

    public ContactEntity() {}

    public ContactEntity(String contactName, String email, String phone,
                         String designation, CompanyEntity company) {
        this.contactName = contactName;
        this.email = email;
        this.phone = phone;
        this.designation = designation;
        this.company = company;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getContactName() { return contactName; }
    public void setContactName(String contactName) { this.contactName = contactName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public CompanyEntity getCompany() { return company; }
    public void setCompany(CompanyEntity company) { this.company = company; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
