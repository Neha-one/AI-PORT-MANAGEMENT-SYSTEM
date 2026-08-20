package com.portmanagement.backend.dto;

import com.portmanagement.backend.entity.Vessel;
import com.portmanagement.backend.entity.VesselStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VesselResponse {

    private Long id;
    private String vesselId;
    private String vesselName;
    private String shipType;
    private Double length;
    private Double draftDepth;
    private LocalDateTime arrivalEta;
    private LocalDateTime departureEtd;
    private VesselStatus status;
    private BerthResponse assignedBerth;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static VesselResponse fromEntity(Vessel vessel) {
        if (vessel == null) return null;
        return VesselResponse.builder()
                .id(vessel.getId())
                .vesselId(vessel.getVesselId())
                .vesselName(vessel.getVesselName())
                .shipType(vessel.getShipType())
                .length(vessel.getLength())
                .draftDepth(vessel.getDraftDepth())
                .arrivalEta(vessel.getArrivalEta())
                .departureEtd(vessel.getDepartureEtd())
                .status(vessel.getStatus())
                .assignedBerth(vessel.getAssignedBerth() != null ? BerthResponse.fromEntity(vessel.getAssignedBerth()) : null)
                .createdAt(vessel.getCreatedAt())
                .updatedAt(vessel.getUpdatedAt())
                .build();
    }
}
