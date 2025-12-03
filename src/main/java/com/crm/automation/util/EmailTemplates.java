package com.crm.automation.util;

public class EmailTemplates {
	// Lead Notification Template
    public static String leadCreated(String leadName) {
        return "A new lead has been added: " + leadName +
                "\nPlease review and take necessary action.";
    }

    public static String leadQualified(String leadName) {
        return "Lead qualified: " + leadName +
                "\nAutomation triggered for Opportunity creation.";
    }


    // Opportunity Notification Template
    public static String opportunityStageChanged(String oppName, String stage) {
        return "Opportunity '" + oppName +
                "' status changed to: " + stage +
                "\nPlease review next steps.";
    }


    // Quote Notification Template
    public static String quoteAccepted(String oppName) {
        return "Quote for Opportunity '" + oppName +
                "' has been ACCEPTED!" +
                "\nSales Order will be generated automatically.";
    }


    // Sales Order Notification Template
    public static String salesOrderCompleted(String soNumber) {
        return "Sales Order #" + soNumber +
                " has been completed.\nInvoice creation triggered.";
    }


    // Invoice Notification Template
    public static String invoiceOverdue(String invoiceNumber) {
        return "Invoice #" + invoiceNumber + 
                " is OVERDUE!\nPlease follow up immediately.";
    }


    // Ticket Notification Template
    public static String ticketEscalated(String string) {
        return "Ticket ID: " + string +
                " has been ESCALATED due to SLA breach!" +
                "\nImmediate attention required.";
    }

    public static String ticketResolved(Long ticketId) {
        return "Ticket ID: " + ticketId + 
                " has been RESOLVED.\nWaiting for customer confirmation.";
    }


}
