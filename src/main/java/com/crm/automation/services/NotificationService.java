package com.crm.automation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.automation.entity.InvoiceEntity;
import com.crm.automation.entity.LeadEntity;
import com.crm.automation.entity.QuoteEntity;
import com.crm.automation.entity.TicketEntity;
import com.crm.automation.util.EmailTemplates;

@Service
public class NotificationService {

    @Autowired
    private EmailService emailService;

    // 📩 Lead Qualified
    public void sendLeadQualifiedAlert(LeadEntity lead) {

        String toEmail = lead.getEmail();
        String subject = "Lead Qualified: " + lead.getLeadName();
        String body = EmailTemplates.leadQualified(lead.getLeadName());

        System.out.println(" Sending Lead Qualified Notification to " + toEmail);
        emailService.sendEmail(toEmail, subject, body);
    }

    // 📩 Quote Accepted
    public void sendQuoteAcceptedNotification(QuoteEntity quote) {

        String toEmail = quote.getCustomerEmail();
        String subject = "Quote Accepted: " + quote.getQuoteNumber();
        String body = EmailTemplates.quoteAccepted(quote.getQuoteNumber());

        System.out.println(" Sending Quote Accepted Email to customer");
        emailService.sendEmail(toEmail, subject, body);
    }

    // 📩 Invoice Paid
    public void sendInvoicePaidNotification(InvoiceEntity invoice) {

        String toEmail = invoice.getCustomerEmail();
        String subject = "Invoice Paid: " + invoice.getInvoiceNumber();
        String body = EmailTemplates.invoiceOverdue(invoice.getInvoiceNumber());

        System.out.println(" Sending Invoice Paid Confirmation Email");
        emailService.sendEmail(toEmail, subject, body);
    }

    // 🆕 Ticket Created
    public void sendTicketCreationAlert(TicketEntity ticket) {

        String toEmail = ticket.getCustomerEmail();
        String subject = "Support Ticket Created: " + ticket.getTicketNumber();
        String body = EmailTemplates.leadCreated(ticket.getTicketNumber());

        System.out.println(" Sending Ticket Created Alert → " + toEmail);
        emailService.sendEmail(toEmail, subject, body);
    }

    // 🚨 Ticket Escalated (SLA Breach)
    public void sendTicketEscalationAlert(TicketEntity ticket) {

        String toEmail = ticket.getCustomerEmail();
        String subject = " SLA Breached → Ticket Escalated: " + ticket.getTicketNumber();
        String body = EmailTemplates.ticketEscalated(ticket.getTicketNumber());

        System.out.println(" Sending SLA Breach Escalation Email");
        emailService.sendEmail(toEmail, subject, body);
    }

	
		public void sendTicketSlaBreachNotification(TicketEntity ticket) {

		    String subject = " SLA Breached - Ticket " + ticket.getTicketNumber();

		    String message = "Ticket SLA time is breached.\n\n" +
		            "Ticket Number: " + ticket.getTicketNumber() + "\n" +
		            "Subject: " + ticket.getSubject() + "\n" +
		            "Priority: " + ticket.getPriority() + "\n" +
		            "Assigned To: " + ticket.getAssignedTo() + "\n" +
		            "Customer Email: " + ticket.getCustomerEmail() + "\n" +
		            "SLA Due Time: " + ticket.getSlaDueTime() + "\n\n" +
		            "Please take immediate action.";

		    System.out.println(" SLA Breach Detected for Ticket: " + ticket.getTicketNumber());

		    // Send Email Notification
		    if (ticket.getCustomerEmail() != null && !ticket.getCustomerEmail().isEmpty()) {
		        emailService.sendEmail(ticket.getCustomerEmail(), subject, message);
		    }

		    // Send SMS Notification
		    if (ticket.getCustomerPhone() != null && !ticket.getCustomerPhone().isEmpty()) {
		        try {
		            SMSService.sendSMS(ticket.getCustomerPhone(),
		                    "SLA Breached! Ticket: " + ticket.getTicketNumber());
		            System.out.println(" SLA breach SMS sent to " + ticket.getCustomerPhone());
		        } catch (Exception e) {
		            System.out.println(" Failed to send SLA breach SMS: " + e.getMessage());
		        }

		    }}
		
	}
