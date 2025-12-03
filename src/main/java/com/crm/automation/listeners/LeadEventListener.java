package com.crm.automation.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.crm.automation.entity.LeadEntity;
import com.crm.automation.entity.OpportunityEntity;
import com.crm.automation.enums.OpportunityStatus;
import com.crm.automation.events.LeadQualifiedEvent;
import com.crm.automation.repositories.OpportunityRepository;
import com.crm.automation.services.NotificationService;

@Component
public class LeadEventListener {
	@Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private NotificationService notificationService;

    @EventListener
    public void handleLeadQualifiedEvent(LeadQualifiedEvent event) {

        LeadEntity lead = event.getLead();
        System.out.println("EVENT: Lead Qualified → " + lead.getLeadName());

        // 1️ Auto-create Opportunity from Lead
        OpportunityEntity opportunity = new OpportunityEntity();
        opportunity.setName("Opportunity - " + lead.getLeadName());
        opportunity.setExpectedRevenue(50000.0);
        opportunity.setRegion(lead.getRegion());
        opportunity.setStage(OpportunityStatus.QUALIFIED);
        opportunity.setLead(lead);
        opportunity.setAssignedTo("Sales Team");

        opportunityRepository.save(opportunity);
        System.out.println(" Opportunity Auto-Created for Lead: " + lead.getLeadName());

        // 2️ Send Notification Email
        notificationService.sendLeadQualifiedAlert(lead);
    }


}
