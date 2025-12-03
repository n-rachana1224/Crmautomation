package com.crm.automation.enums;

public enum TicketStatus {
	
    OPEN,        // Ticket created and waiting for action
    IN_PROGRESS, // Work started by support team
    ESCALATED,   // SLA breached or delayed response
    RESOLVED,    // Issue fixed but pending confirmation
    CLOSED       // Confirmed and finalized

	

}
