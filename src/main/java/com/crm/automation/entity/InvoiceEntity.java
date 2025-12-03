package com.crm.automation.entity;

import java.time.LocalDateTime;

import com.crm.automation.enums.InvoiceStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "invoices")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;
    private Double amount;
    private LocalDateTime dueDate;
    private String assignedTo;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status; // DRAFT → SENT → PAID → OVERDUE

    @ManyToOne
    @JoinColumn(name = "sales_order_id")
    private SalesOrderEntity salesOrder; // Invoice belongs to Sales Order

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime resolvedAt; // When invoice is paid

    public InvoiceEntity() {}

    public InvoiceEntity(String invoiceNumber, Double amount, LocalDateTime dueDate,
                         InvoiceStatus status, String assignedTo, SalesOrderEntity salesOrder) {
        this.invoiceNumber = invoiceNumber;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
        this.assignedTo = assignedTo;
        this.salesOrder = salesOrder;
        this.createdAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public InvoiceStatus getStatus() { return status; }
    public void setStatus(InvoiceStatus status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public SalesOrderEntity getSalesOrder() { return salesOrder; }
    public void setSalesOrder(SalesOrderEntity salesOrder) { this.salesOrder = salesOrder; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }

	public String getCustomerEmail() {
		// TODO Auto-generated method stub
		return null;
	}
}
