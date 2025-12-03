package com.crm.automation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crm.automation.entity.QuoteEntity;
import com.crm.automation.enums.QuoteStatus;
import com.crm.automation.repositories.QuoteRepository;
import com.crm.automation.services.StatusService;

@RestController
@RequestMapping("/api/quotes")
@CrossOrigin
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private StatusService statusService;

    // Create Quote
    @PostMapping
    public QuoteEntity createQuote(@RequestBody QuoteEntity quote) {
        if (quote.getStatus() == null) {
            quote.setStatus(QuoteStatus.CREATED);
        }
        return quoteRepository.save(quote);
    }

    // Get All Quotes
    @GetMapping
    public List<QuoteEntity> getAllQuotes() {
        return quoteRepository.findAll();
    }

    // Get Quote By ID
    @GetMapping("/{id}")
    public Optional<QuoteEntity> getQuoteById(@PathVariable Long id) {
        return quoteRepository.findById(id);
    }

    // Update Quote Status (Automation Trigger)
    @PutMapping("/{id}/status")
    public QuoteEntity updateQuoteStatus(
            @PathVariable Long id,
            @RequestParam QuoteStatus status) {

        return statusService.updateQuoteStatus(id, status);
    }

    // Delete Quote
    @DeleteMapping("/{id}")
    public String deleteQuote(@PathVariable Long id) {
        quoteRepository.deleteById(id);
        return "Quote Deleted Successfully!";
    }
}
