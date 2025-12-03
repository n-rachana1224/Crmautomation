package com.crm.automation.events;

import org.springframework.context.ApplicationEvent;

import com.crm.automation.entity.TicketEntity;

public class TicketSlaBreachedEvent extends ApplicationEvent {
	private final TicketEntity ticket;

    public TicketSlaBreachedEvent(Object source, TicketEntity ticket) {
        super(source);
        this.ticket = ticket;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

}
