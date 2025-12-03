package com.crm.automation.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crm.automation.entity.TicketEntity;
import com.crm.automation.enums.TicketStatus;
import com.crm.automation.repositories.TicketRepository;
import com.crm.automation.services.StatusService;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private StatusService statusService;

    // Create Ticket
    @PostMapping
    public TicketEntity createTicket(@RequestBody TicketEntity ticket) {
        ticket.setCreatedAt(LocalDateTime.now());

        if (ticket.getStatus() == null) {
            ticket.setStatus(TicketStatus.OPEN);
        }

        // Default SLA: 24 hours
        if (ticket.getSlaDueTime() == null) {
            ticket.setSlaDueTime(LocalDateTime.now().plusHours(24));
        }

        return ticketRepository.save(ticket);
    }

    // Get All Tickets
    @GetMapping
    public List<TicketEntity> getAllTickets() {
        return ticketRepository.findAll();
    }

    // Get Ticket By ID
    @GetMapping("/{id}")
    public Optional<TicketEntity> getTicketById(@PathVariable Long id) {
        return ticketRepository.findById(id);
    }

    // Update Status (Automation Trigger)
    @PutMapping("/{id}/status")
    public TicketEntity updateTicketStatus(
            @PathVariable Long id,
            @RequestParam TicketStatus status) {

        return statusService.updateTicketStatus(id, status);
    }

    // Delete Ticket
    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id) {
        ticketRepository.deleteById(id);
        return "Ticket Deleted Successfully!";
    }
}
