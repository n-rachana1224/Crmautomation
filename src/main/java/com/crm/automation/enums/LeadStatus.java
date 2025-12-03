package com.crm.automation.enums;

public enum LeadStatus {
	NEW,          // Lead newly registered in system
    CONTACTED,    // Sales team reached out
    QUALIFIED,    // Lead shows interest & is valid
    JUNK,         // Not a valid lead (invalid data / no interest)
    CONVERTED     // Qualified lead converted to customer (Company + Contact created)

	

}
