package com.crm.automation.entity;

import java.time.LocalDateTime;

import com.crm.automation.enums.QuoteStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "quotes")
public class QuoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quoteNumber;
    private Double totalAmount;
    private String assignedTo;

    @Enumerated(EnumType.STRING)
    private QuoteStatus status;

    @ManyToOne
    @JoinColumn(name = "opportunity_id")
    private OpportunityEntity opportunity;

    private String customerEmail;
    private String customerPhone;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public QuoteEntity() {}

    public QuoteEntity(String quoteNumber, Double totalAmount, String assignedTo,
                       QuoteStatus status, OpportunityEntity opportunity,
                       String customerEmail, String customerPhone) {

        this.quoteNumber = quoteNumber;
        this.totalAmount = totalAmount;
        this.assignedTo = assignedTo;
        this.status = status;
        this.opportunity = opportunity;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.createdAt = LocalDateTime.now();
    }

    // Getters & Setters

    public Long getId() { return id; }

    public String getQuoteNumber() { return quoteNumber; }
    public void setQuoteNumber(String quoteNumber) { this.quoteNumber = quoteNumber; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public QuoteStatus getStatus() { return status; }
    public void setStatus(QuoteStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public OpportunityEntity getOpportunity() { return opportunity; }
    public void setOpportunity(OpportunityEntity opportunity) { this.opportunity = opportunity; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
