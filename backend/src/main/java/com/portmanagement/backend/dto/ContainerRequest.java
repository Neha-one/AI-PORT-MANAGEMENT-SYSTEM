package com.portmanagement.backend.dto;

import com.portmanagement.backend.entity.ContainerStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContainerRequest {

    @NotBlank(message = "Container ID is required")
    private String containerId;

    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be positive")
    private Double weight;

    @NotBlank(message = "Cargo type is required")
    private String cargoType;

    private Long assignedVesselId;

    private String assignedYardLocation;

    private ContainerStatus status;
}
