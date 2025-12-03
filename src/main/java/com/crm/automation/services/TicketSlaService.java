package com.crm.automation.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.crm.automation.entity.TicketEntity;
import com.crm.automation.enums.TicketStatus;
import com.crm.automation.events.TicketSlaBreachedEvent;
import com.crm.automation.repositories.TicketRepository;

@Service
public class TicketSlaService {
	 @Autowired
	    private TicketRepository ticketRepository;

	    @Autowired
	    private ApplicationEventPublisher eventPublisher;


	    /**
	     * SLA Processing Logic:
	     * Escalate tickets that passed SLA due time
	     */
	    public void processSlaBreaches() {
	        
	        List<TicketEntity> tickets = ticketRepository.findAll();

	        for (TicketEntity ticket : tickets) {

	            if (ticket.getSlaDueTime() != null &&
	                ticket.getSlaDueTime().isBefore(LocalDateTime.now()) &&
	                (ticket.getStatus() == TicketStatus.OPEN ||
	                 ticket.getStatus() == TicketStatus.IN_PROGRESS)) {

	                ticket.setStatus(TicketStatus.ESCALATED);
	                ticketRepository.save(ticket);

	                // 🔔 Trigger escalation event
	                eventPublisher.publishEvent(new TicketSlaBreachedEvent(this, ticket));

	                System.out.println("🚨 SLA Breached — Ticket ID: " + ticket.getId());
	            }
	        }
	    }
	

}
