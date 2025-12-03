package com.crm.automation.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.crm.automation.entity.InvoiceEntity;
import com.crm.automation.events.InvoicePaidEvent;
import com.crm.automation.services.NotificationService;

@Component
public class InvoiceEventListener {
	 @Autowired
	    private NotificationService notificationService;

	    @EventListener
	    public void handleInvoicePaidEvent(InvoicePaidEvent event) {
	        InvoiceEntity invoice = event.getInvoice();
	        
	        System.out.println("EVENT: Invoice Paid → " + invoice.getInvoiceNumber());
	        
	        notificationService.sendInvoicePaidNotification(invoice);

	        // Future workflow automation:
	        // ✔ Update Financial records
	        // ✔ Send payment receipt to customer
	        // ✔ Trigger analytics update
	    }

}
