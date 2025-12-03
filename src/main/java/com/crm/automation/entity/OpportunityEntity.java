package com.crm.automation.entity;

import java.time.LocalDateTime;

import com.crm.automation.enums.OpportunityStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "opportunities")
public class OpportunityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double expectedRevenue;
    private String region;
    private String assignedTo;

    @Enumerated(EnumType.STRING)
    private OpportunityStatus stage;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    private LeadEntity lead;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    private LocalDateTime createdAt = LocalDateTime.now();

    public OpportunityEntity() {}

    public OpportunityEntity(String name, Double expectedRevenue,
                             String region, OpportunityStatus stage,
                             String assignedTo, LeadEntity lead,
                             CompanyEntity company) {
        this.name = name;
        this.expectedRevenue = expectedRevenue;
        this.region = region;
        this.stage = stage;
        this.assignedTo = assignedTo;
        this.lead = lead;
        this.company = company;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getExpectedRevenue() { return expectedRevenue; }
    public void setExpectedRevenue(Double expectedRevenue) { this.expectedRevenue = expectedRevenue; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public OpportunityStatus getStage() { return stage; }
    public void setStage(OpportunityStatus stage) { this.stage = stage; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public LeadEntity getLead() { return lead; }
    public void setLead(LeadEntity lead) { this.lead = lead; }

    public CompanyEntity getCompany() { return company; }
    public void setCompany(CompanyEntity company) { this.company = company; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
