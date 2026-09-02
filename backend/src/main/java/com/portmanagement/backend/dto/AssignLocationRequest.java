package com.portmanagement.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignLocationRequest {

    @NotBlank(message = "Assigned yard location is required")
    private String assignedYardLocation;
}
