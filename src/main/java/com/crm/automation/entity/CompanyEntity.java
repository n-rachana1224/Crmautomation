package com.crm.automation.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String industry;
    private String website;
    private String email;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<ContactEntity> contacts;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<OpportunityEntity> opportunities;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<TicketEntity> tickets;

    private LocalDateTime createdAt = LocalDateTime.now();

    public CompanyEntity() {}

    public CompanyEntity(String companyName, String industry, String website,
                         String email, String phone, String address) {
        this.companyName = companyName;
        this.industry = industry;
        this.website = website;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getters & Setters

    public Long getId() { return id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public List<ContactEntity> getContacts() { return contacts; }
    public void setContacts(List<ContactEntity> contacts) { this.contacts = contacts; }

    public List<OpportunityEntity> getOpportunities() { return opportunities; }
    public void setOpportunities(List<OpportunityEntity> opportunities) { this.opportunities = opportunities; }

    public List<TicketEntity> getTickets() { return tickets; }
    public void setTickets(List<TicketEntity> tickets) { this.tickets = tickets; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
