package com.portmanagement.backend.dto;

import com.portmanagement.backend.entity.VesselStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VesselRequest {

    @NotBlank(message = "Vessel ID is required")
    private String vesselId;

    @NotBlank(message = "Vessel name is required")
    private String vesselName;

    @NotBlank(message = "Ship type is required")
    private String shipType;

    @NotNull(message = "Vessel length is required")
    @Positive(message = "Length must be positive")
    private Double length;

    @NotNull(message = "Draft depth is required")
    @Positive(message = "Draft depth must be positive")
    private Double draftDepth;

    @NotNull(message = "Arrival ETA is required")
    private LocalDateTime arrivalEta;

    private LocalDateTime departureEtd;

    private VesselStatus status;

    private Long assignedBerthId;
}
