package com.develop.springmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class TicketDTO {
    private UUID id;
    private UUID userId;
    @EqualsAndHashCode.Include
    private UUID eventId;
    @EqualsAndHashCode.Include
    private int place;
}
