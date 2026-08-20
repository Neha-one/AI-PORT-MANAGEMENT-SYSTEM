package com.portmanagement.backend.dto;

import com.portmanagement.backend.entity.VesselStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VesselScheduleUpdateRequest {

    @NotNull(message = "Arrival ETA is required")
    private LocalDateTime arrivalEta;

    private LocalDateTime departureEtd;

    private VesselStatus status;
}
