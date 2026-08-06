package com.portmanagement.backend.dto;

import com.portmanagement.backend.entity.Container;
import com.portmanagement.backend.entity.ContainerStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContainerResponse {

    private Long id;
    private String containerId;
    private Double weight;
    private String cargoType;
    private VesselResponse assignedVessel;
    private String assignedYardLocation;
    private ContainerStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ContainerResponse fromEntity(Container container) {
        if (container == null) return null;
        return ContainerResponse.builder()
                .id(container.getId())
                .containerId(container.getContainerId())
                .weight(container.getWeight())
                .cargoType(container.getCargoType())
                .assignedVessel(container.getAssignedVessel() != null ? VesselResponse.fromEntity(container.getAssignedVessel()) : null)
                .assignedYardLocation(container.getAssignedYardLocation())
                .status(container.getStatus())
                .createdAt(container.getCreatedAt())
                .updatedAt(container.getUpdatedAt())
                .build();
    }
}
