package com.crm.automation.enums;

public enum SalesOrderStatus {
	CREATED,     // Sales Order generated from Quote
    APPROVED,    // Management or Customer approved
    REJECTED,    // Customer rejected after review
    COMPLETED    // Order fulfilled - move to Invoice

}
