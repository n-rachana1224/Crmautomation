package com.crm.automation.events;

import org.springframework.context.ApplicationEvent;

import com.crm.automation.entity.TicketEntity;

public class TicketCreatedEvent extends ApplicationEvent {
	private final TicketEntity ticket;

    public TicketCreatedEvent(Object source, TicketEntity ticket) {
        super(source);
        this.ticket = ticket;
    }

    public TicketEntity getTicket() {
        return ticket;
    }



}
