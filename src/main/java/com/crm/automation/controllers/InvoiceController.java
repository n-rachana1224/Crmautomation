package com.crm.automation.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crm.automation.entity.InvoiceEntity;
import com.crm.automation.enums.InvoiceStatus;
import com.crm.automation.repositories.InvoiceRepository;
import com.crm.automation.services.StatusService;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private StatusService statusService;

    // Create Invoice
    @PostMapping
    public InvoiceEntity createInvoice(@RequestBody InvoiceEntity invoice) {
        invoice.setCreatedAt(LocalDateTime.now());
        invoice.setStatus(InvoiceStatus.DRAFT);
        return invoiceRepository.save(invoice);
    }

    // Get All Invoices
    @GetMapping
    public List<InvoiceEntity> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    // Get Invoice By ID
    @GetMapping("/{id}")
    public Optional<InvoiceEntity> getInvoiceById(@PathVariable Long id) {
        return invoiceRepository.findById(id);
    }

    // Update Status (with automation trigger)
    @PutMapping("/{id}/status")
    public InvoiceEntity updateStatus(
            @PathVariable Long id,
            @RequestParam InvoiceStatus status) {

        return statusService.updateInvoiceStatus(id, status);
    }

    // Delete Invoice
    @DeleteMapping("/{id}")
    public String deleteInvoice(@PathVariable Long id) {
        invoiceRepository.deleteById(id);
        return "Invoice deleted successfully.";
    }
}
