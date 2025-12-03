package com.crm.automation.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.crm.automation.entity.QuoteEntity;
import com.crm.automation.entity.SalesOrderEntity;
import com.crm.automation.enums.SalesOrderStatus;
import com.crm.automation.events.QuoteFinalizedEvent;
import com.crm.automation.repositories.SalesOrderRepository;
import com.crm.automation.services.NotificationService;

@Component
public class QuoteEventListener {
	@Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private NotificationService notificationService;

    @EventListener
    public void handleQuoteFinalizedEvent(QuoteFinalizedEvent event) {

        QuoteEntity quote = event.getQuote();

        System.out.println(" EVENT: Quote Finalized → " + quote.getQuoteNumber());

        // 1️ Auto-create Sales Order from Quote
        SalesOrderEntity order = new SalesOrderEntity();
        order.setOrderNumber("SO-" + quote.getId());
        order.setTotalAmount(quote.getTotalAmount());
        order.setAssignedTo(quote.getAssignedTo());
        order.setQuote(quote);
        order.setStatus(SalesOrderStatus.CREATED);

        salesOrderRepository.save(order);
        System.out.println("Sales Order Auto-Created for Quote: " + quote.getQuoteNumber());

        // 2️ Trigger Notification
        notificationService.sendQuoteAcceptedNotification(quote);
    }


}
