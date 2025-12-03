package com.crm.automation.entity;

import java.time.LocalDateTime;
import com.crm.automation.enums.SalesOrderStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "sales_orders")
public class SalesOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private Double totalAmount;
    private String assignedTo;

    @Enumerated(EnumType.STRING)
    private SalesOrderStatus status;
    // CREATED → APPROVED → REJECTED → COMPLETED

    @ManyToOne
    @JoinColumn(name = "quote_id")
    private QuoteEntity quote;

    // For notifications
    private String customerEmail;
    private String customerPhone;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public SalesOrderEntity() {}

    public SalesOrderEntity(String orderNumber, Double totalAmount, SalesOrderStatus status,
                            String assignedTo, QuoteEntity quote, String customerEmail,
                            String customerPhone) {

        this.orderNumber = orderNumber;
        this.totalAmount = totalAmount;
        this.status = status;
        this.assignedTo = assignedTo;
        this.quote = quote;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.createdAt = LocalDateTime.now();
    }

    // GETTERS & SETTERS

    public Long getId() { return id; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public SalesOrderStatus getStatus() { return status; }
    public void setStatus(SalesOrderStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now(); // track stage changes
    }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public QuoteEntity getQuote() { return quote; }
    public void setQuote(QuoteEntity quote) { this.quote = quote; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

	public void setCreatedAt(LocalDateTime now) {
		this.createdAt=createdAt;	
	}
}
