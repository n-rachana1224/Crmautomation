package com.crm.automation.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.crm.automation.entity.InvoiceEntity;
import com.crm.automation.entity.LeadEntity;
import com.crm.automation.entity.OpportunityEntity;
import com.crm.automation.entity.QuoteEntity;
import com.crm.automation.entity.SalesOrderEntity;
import com.crm.automation.entity.TicketEntity;
import com.crm.automation.enums.InvoiceStatus;
import com.crm.automation.enums.LeadStatus;
import com.crm.automation.enums.OpportunityStatus;
import com.crm.automation.enums.QuoteStatus;
import com.crm.automation.enums.SalesOrderStatus;
import com.crm.automation.enums.TicketStatus;
import com.crm.automation.events.InvoicePaidEvent;
import com.crm.automation.events.LeadQualifiedEvent;
import com.crm.automation.events.QuoteFinalizedEvent;
import com.crm.automation.repositories.InvoiceRepository;
import com.crm.automation.repositories.LeadRepository;
import com.crm.automation.repositories.OpportunityRepository;
import com.crm.automation.repositories.QuoteRepository;
import com.crm.automation.repositories.SalesOrderRepository;
import com.crm.automation.repositories.TicketRepository;

@Service
public class StatusService {
	@Autowired
    private LeadRepository leadRepository;

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    /* ---------------- LEAD STATUS UPDATE ---------------- */
    public LeadEntity updateLeadStatus(Long id, LeadStatus status) {
        LeadEntity lead = leadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead Not Found"));

        lead.setStatus(status);
        leadRepository.save(lead);

        // Automation Trigger → Lead Qualified
        if (status == LeadStatus.QUALIFIED) {
            eventPublisher.publishEvent(new LeadQualifiedEvent(this, lead));
        }

        return lead;
    }


    /* ---------------- OPPORTUNITY STATUS UPDATE ---------------- */
    public OpportunityEntity updateOpportunityStatus(Long id, OpportunityStatus status) {
        OpportunityEntity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Opportunity Not Found"));

        opportunity.setStage(status);
        opportunityRepository.save(opportunity);
        return opportunity;
    }


    /* ---------------- QUOTE STATUS UPDATE ---------------- */
    public QuoteEntity updateQuoteStatus(Long id, QuoteStatus status) {
        QuoteEntity quote = quoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quote Not Found"));

        quote.setStatus(status);
        quoteRepository.save(quote);

        // Trigger Finalization Event
        if (status == QuoteStatus.ACCEPTED || status == QuoteStatus.REJECTED) {
            eventPublisher.publishEvent(new QuoteFinalizedEvent(this, quote));
        }

        return quote;
    }


    /* ---------------- SALES ORDER STATUS UPDATE ---------------- */
    public SalesOrderEntity updateSalesOrderStatus(Long id, SalesOrderStatus status) {
        SalesOrderEntity order = salesOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales Order Not Found"));

        order.setStatus(status);
        salesOrderRepository.save(order);
        return order;
    }


    /* ---------------- INVOICE STATUS UPDATE ---------------- */
    public InvoiceEntity updateInvoiceStatus(Long id, InvoiceStatus status) {
        InvoiceEntity invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice Not Found"));

        invoice.setStatus(status);

        if (status == InvoiceStatus.PAID) {
            invoice.setResolvedAt(LocalDateTime.now());
            eventPublisher.publishEvent(new InvoicePaidEvent(this, invoice));
        }

        invoiceRepository.save(invoice);
        return invoice;
    }


    /* ---------------- TICKET STATUS UPDATE ---------------- */
    public TicketEntity updateTicketStatus(Long id, TicketStatus status) {
        TicketEntity ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket Not Found"));

        ticket.setStatus(status);

        if (status == TicketStatus.RESOLVED || status == TicketStatus.CLOSED) {
            ticket.setResolvedAt(LocalDateTime.now());
        }

        ticketRepository.save(ticket);
        return ticket;
    }


}
