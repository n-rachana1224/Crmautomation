package com.crm.automation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crm.automation.entity.OpportunityEntity;
import com.crm.automation.enums.OpportunityStatus;
import com.crm.automation.repositories.OpportunityRepository;
import com.crm.automation.services.StatusService;

@RestController
@RequestMapping("/api/opportunities")
@CrossOrigin
public class OpportunityController {

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private StatusService statusService;

    // Create Opportunity
    @PostMapping
    public OpportunityEntity createOpportunity(@RequestBody OpportunityEntity opportunity) {
        if (opportunity.getStage() == null) {
            opportunity.setStage(OpportunityStatus.QUALIFIED);
        }
        return opportunityRepository.save(opportunity);
    }

    // Get All Opportunities
    @GetMapping
    public List<OpportunityEntity> getAllOpportunities() {
        return opportunityRepository.findAll();
    }

    // Get By ID
    @GetMapping("/{id}")
    public Optional<OpportunityEntity> getOpportunityById(@PathVariable Long id) {
        return opportunityRepository.findById(id);
    }

    // Update Stage (Automation Trigger)
    @PutMapping("/{id}/stage")
    public OpportunityEntity updateStage(
            @PathVariable Long id,
            @RequestParam OpportunityStatus status) {
        return statusService.updateOpportunityStatus(id, status);
    }

    // Delete Opportunity
    @DeleteMapping("/{id}")
    public String deleteOpportunity(@PathVariable Long id) {
        opportunityRepository.deleteById(id);
        return "Opportunity Deleted Successfully!";
    }
}
