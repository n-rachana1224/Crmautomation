package com.crm.automation.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.automation.entity.CompanyEntity;
import com.crm.automation.entity.ContactEntity;
import com.crm.automation.entity.InvoiceEntity;
import com.crm.automation.entity.LeadEntity;
import com.crm.automation.entity.OpportunityEntity;
import com.crm.automation.entity.QuoteEntity;
import com.crm.automation.entity.SalesOrderEntity;
import com.crm.automation.enums.InvoiceStatus;
import com.crm.automation.enums.LeadStatus;
import com.crm.automation.enums.OpportunityStatus;
import com.crm.automation.enums.QuoteStatus;
import com.crm.automation.enums.SalesOrderStatus;
import com.crm.automation.repositories.CompanyRepository;
import com.crm.automation.repositories.ContactRepository;
import com.crm.automation.repositories.InvoiceRepository;
import com.crm.automation.repositories.LeadRepository;
import com.crm.automation.repositories.OpportunityRepository;
import com.crm.automation.repositories.QuoteRepository;
import com.crm.automation.repositories.SalesOrderRepository;

@Service
public class WorkflowService {
	@Autowired
    private LeadRepository leadRepository;
    
    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private ContactRepository contactRepository;
    
    @Autowired
    private OpportunityRepository opportunityRepository;
    
    @Autowired
    private QuoteRepository quoteRepository;
    
    @Autowired
    private SalesOrderRepository salesOrderRepository;
    
    @Autowired
    private InvoiceRepository invoiceRepository;


    /* ---------------- LEAD → COMPANY + CONTACT + OPPORTUNITY ---------------- */
    public OpportunityEntity convertLeadToOpportunity(LeadEntity lead) {

        // Create Company
        CompanyEntity company = new CompanyEntity();
        company.setCompanyName(lead.getCompanyName());
        company.setEmail(lead.getEmail());
        company.setPhone(lead.getPhone());
        companyRepository.save(company);

        // Create Contact
        ContactEntity contact = new ContactEntity();
        contact.setContactName(lead.getLeadName());
        contact.setEmail(lead.getEmail());
        contact.setPhone(lead.getPhone());
        contact.setCompany(company);
        contactRepository.save(contact);

        // Create Opportunity
        OpportunityEntity opportunity = new OpportunityEntity();
        opportunity.setName("Opportunity - " + lead.getLeadName());
        opportunity.setStage(OpportunityStatus.QUALIFIED);
        opportunity.setCreatedAt(LocalDateTime.now());
        opportunity.setCompany(company);
        opportunityRepository.save(opportunity);

        // Update Lead Status
        lead.setStatus(LeadStatus.CONVERTED);
        leadRepository.save(lead);

        return opportunity;
    }



    /* ---------------- OPPORTUNITY → QUOTE ---------------- */
    public QuoteEntity createQuoteForOpportunity(Long opportunityId, double amount) {

        OpportunityEntity opportunity = opportunityRepository.findById(opportunityId)
                .orElseThrow(() -> new RuntimeException("Opportunity Not Found"));

        QuoteEntity quote = new QuoteEntity();
        quote.setOpportunity(opportunity);
        quote.setTotalAmount(amount);
        quote.setStatus(QuoteStatus.CREATED);
        quoteRepository.save(quote);

        return quote;
    }



    /* ---------------- QUOTE ACCEPTED → SALES ORDER ---------------- */
    public SalesOrderEntity convertQuoteToSalesOrder(QuoteEntity quote) {

        SalesOrderEntity order = new SalesOrderEntity();
        order.setQuote(quote);
        order.setStatus(SalesOrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        salesOrderRepository.save(order);

        return order;
    }



    /* ---------------- SALES ORDER COMPLETED → INVOICE ---------------- */
    public InvoiceEntity generateInvoiceFromSalesOrder(Long salesOrderId, double invoiceAmount) {

        SalesOrderEntity order = salesOrderRepository.findById(salesOrderId)
                .orElseThrow(() -> new RuntimeException("Sales Order Not Found"));

        InvoiceEntity invoice = new InvoiceEntity();
        invoice.setSalesOrder(order);
        invoice.setAmount(invoiceAmount);
        invoice.setStatus(InvoiceStatus.SENT);
        invoice.setCreatedAt(LocalDateTime.now());
        invoiceRepository.save(invoice);

        return invoice;
    }



}
