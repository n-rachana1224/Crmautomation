package com.crm.automation.events;

import org.springframework.context.ApplicationEvent;

import com.crm.automation.entity.LeadEntity;

public class LeadQualifiedEvent extends ApplicationEvent {
	private final LeadEntity lead;

    public LeadQualifiedEvent(Object source, LeadEntity lead) {
        super(source);
        this.lead = lead;
    }

    public LeadEntity getLead() {
        return lead;
    }


}
