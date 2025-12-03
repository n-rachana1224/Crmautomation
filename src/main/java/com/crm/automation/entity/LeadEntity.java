package com.crm.automation.entity;

import java.time.LocalDateTime;

import com.crm.automation.enums.LeadStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "leads")
public class LeadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String leadName;
    private String companyName;
    private String email;
    private String phone;
    private String region;
    private String category;

    @Enumerated(EnumType.STRING)
    private LeadStatus status;

    private String assignedTo;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = true)
    private CompanyEntity company; // Link to converted company

    public LeadEntity() {}

    public LeadEntity(String leadName, String companyName, String email, String phone,
                      String region, String category, LeadStatus status, String assignedTo) {
        this.leadName = leadName;
        this.companyName = companyName;
        this.email = email;
        this.phone = phone;
        this.region = region;
        this.category = category;
        this.status = status;
        this.assignedTo = assignedTo;
    }

    // Getters and Setters

    public Long getId() { return id; }

    public String getLeadName() { return leadName; }
    public void setLeadName(String leadName) { this.leadName = leadName; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LeadStatus getStatus() { return status; }
    public void setStatus(LeadStatus status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public CompanyEntity getCompany() { return company; }
    public void setCompany(CompanyEntity company) { this.company = company; }
}
