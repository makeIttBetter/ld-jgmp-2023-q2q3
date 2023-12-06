package com.develop.springmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class EventDTO {
    private UUID id;
    @EqualsAndHashCode.Include
    private String title;
    @EqualsAndHashCode.Include
    private LocalDateTime date;
}
