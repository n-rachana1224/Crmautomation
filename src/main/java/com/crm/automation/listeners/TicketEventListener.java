package com.crm.automation.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.crm.automation.entity.TicketEntity;
import com.crm.automation.events.TicketCreatedEvent;
import com.crm.automation.events.TicketSlaBreachedEvent;
import com.crm.automation.services.NotificationService;

@Component
public class TicketEventListener {
	@Autowired
    private NotificationService notificationService;

    /**
     *  Triggered when a new ticket is created
     *     Send acknowledgment to customer
     */
    @EventListener
    public void handleTicketCreated(TicketCreatedEvent event) {
        TicketEntity ticket = event.getTicket();

        System.out.println("📢EVENT: New Ticket Created → " + ticket.getTicketNumber());

        notificationService.sendTicketCreationAlert(ticket);
    }

    /**
     * Triggered when SLA is breached
     *     Notify support team for escalation
     */
    @EventListener
    public void handleSlaBreachedEvent(TicketSlaBreachedEvent event) {
        TicketEntity ticket = event.getTicket();

        System.out.println("SLA BREACH: Ticket → " + ticket.getTicketNumber());

        notificationService.sendTicketSlaBreachNotification(ticket);
    }


}
