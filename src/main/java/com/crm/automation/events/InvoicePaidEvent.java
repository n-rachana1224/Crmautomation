package com.crm.automation.events;

import org.springframework.context.ApplicationEvent;

import com.crm.automation.entity.InvoiceEntity;

public class InvoicePaidEvent extends ApplicationEvent{
	private final InvoiceEntity invoice;

    public InvoicePaidEvent(Object source, InvoiceEntity invoice) {
        super(source);
        this.invoice = invoice;
    }

    public InvoiceEntity getInvoice() {
        return invoice;
    }

}
