package com.crm.automation.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crm.automation.services.TicketSlaService;

@Component
public class SlaScheduler {
	@Autowired
    private TicketSlaService ticketSlaService;

    
    @Scheduled(fixedRate = 60000)
    public void runSlaCheck() {
        System.out.println(" Running SLA Check for Tickets...");
        ticketSlaService.processSlaBreaches();
    }


}
