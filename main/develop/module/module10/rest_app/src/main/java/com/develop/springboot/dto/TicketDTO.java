package com.develop.springboot.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

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
