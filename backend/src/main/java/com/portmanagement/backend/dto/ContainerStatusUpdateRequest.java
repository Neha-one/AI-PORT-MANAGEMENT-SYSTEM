package com.portmanagement.backend.dto;

import com.portmanagement.backend.entity.ContainerStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContainerStatusUpdateRequest {

    @NotNull(message = "Container status is required")
    private ContainerStatus status;
}
