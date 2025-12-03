package com.crm.automation.events;

import org.springframework.context.ApplicationEvent;

import com.crm.automation.entity.QuoteEntity;

public class QuoteFinalizedEvent extends ApplicationEvent {
	private final QuoteEntity quote;

    public QuoteFinalizedEvent(Object source, QuoteEntity quote) {
        super(source);
        this.quote = quote;
    }

    public QuoteEntity getQuote() {
        return quote;
    }


}
