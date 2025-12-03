package com.crm.automation.enums;

public enum QuoteStatus {
	CREATED,   // Quote drafted inside CRM
    SENT,      // Sent to customer for review
    ACCEPTED,  // Customer approved the quote
    REJECTED,  // Customer rejected the quote
    CLOSED     // Quote finished / no more updates

	
}
