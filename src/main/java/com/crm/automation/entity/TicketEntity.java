package com.crm.automation.entity;

import java.time.LocalDateTime;

import com.crm.automation.enums.TicketStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticketNumber;
    private String subject;

    @Column(length = 1000)
    private String description;

    private String customerEmail;
    private String customerPhone;

    private String assignedTo;

    @Enumerated(EnumType.STRING)
    private TicketStatus status; // OPEN → IN_PROGRESS → RESOLVED → CLOSED → ESCALATED

    private int priority; // 1-High, 2-Medium, 3-Low

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;  // CRM Link: Each ticket belongs to a company

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;

    private LocalDateTime slaDueTime;

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public TicketEntity() {}

    public TicketEntity(String ticketNumber, String subject, String description,
                        String customerEmail, String customerPhone,
                        String assignedTo, TicketStatus status,
                        int priority, LocalDateTime slaDueTime,
                        CompanyEntity company) {

        this.ticketNumber = ticketNumber;
        this.subject = subject;
        this.description = description;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.assignedTo = assignedTo;
        this.status = status;
        this.priority = priority;
        this.slaDueTime = slaDueTime;
        this.company = company;
        this.createdAt = LocalDateTime.now();
    }

    // Getter & Setter Methods

    public Long getId() { return id; }

    public String getTicketNumber() { return ticketNumber; }
    public void setTicketNumber(String ticketNumber) { this.ticketNumber = ticketNumber; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { 
        this.status = status; 
        this.updatedAt = LocalDateTime.now(); 
    }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    public CompanyEntity getCompany() { return company; }
    public void setCompany(CompanyEntity company) { this.company = company; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    
    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }

    public LocalDateTime getSlaDueTime() { return slaDueTime; }
    public void setSlaDueTime(LocalDateTime slaDueTime) { this.slaDueTime = slaDueTime; }

	public void setCreatedAt(LocalDateTime now) {
		this.createdAt=createdAt;
		
	}
}
