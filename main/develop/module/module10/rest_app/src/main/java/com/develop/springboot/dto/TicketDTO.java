package com.develop.springboot.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * DTO for ticket.
 *
 * @see com.develop.springboot.controller.api.ApiTicketController
 */
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class TicketDTO {
    private UUID id;
    private UUID userId;
    @EqualsAndHashCode.Include
    private UUID eventId;
    @EqualsAndHashCode.Include
    private int place;
}
