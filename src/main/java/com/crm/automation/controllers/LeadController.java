package com.crm.automation.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crm.automation.entity.LeadEntity;
import com.crm.automation.enums.LeadStatus;
import com.crm.automation.repositories.LeadRepository;
import com.crm.automation.services.StatusService;

@RestController
@RequestMapping("/api/leads")
@CrossOrigin
public class LeadController {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private StatusService statusService;

    // Create Lead
    @PostMapping
    public LeadEntity createLead(@RequestBody LeadEntity lead) {
        lead.setStatus(LeadStatus.NEW);
        lead.setCreatedAt(LocalDateTime.now());
        return leadRepository.save(lead);
    }

    // Get All Leads
    @GetMapping
    public List<LeadEntity> getAllLeads() {
        return leadRepository.findAll();
    }

    // Get Lead By ID
    @GetMapping("/{id}")
    public Optional<LeadEntity> getLeadById(@PathVariable Long id) {
        return leadRepository.findById(id);
    }

    // Update Lead Status (Automation Trigger)
    @PutMapping("/{id}/status")
    public LeadEntity updateLeadStatus(
            @PathVariable Long id,
            @RequestParam LeadStatus status) {

        return statusService.updateLeadStatus(id, status);
    }

    // Delete Lead
    @DeleteMapping("/{id}")
    public String deleteLead(@PathVariable Long id) {
        leadRepository.deleteById(id);
        return "Lead deleted successfully!";
    }
}
