package com.develop.springboot.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for event.
 *
 * @see com.develop.springboot.controller.api.ApiEventController
 */
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class EventDTO {
    private UUID id;
    @EqualsAndHashCode.Include
    private String title;
    @EqualsAndHashCode.Include
    private LocalDateTime date;
}
