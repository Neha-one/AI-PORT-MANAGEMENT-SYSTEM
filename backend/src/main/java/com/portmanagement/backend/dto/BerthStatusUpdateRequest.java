package com.portmanagement.backend.dto;

import com.portmanagement.backend.entity.BerthStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BerthStatusUpdateRequest {

    @NotNull(message = "Berth status is required")
    private BerthStatus status;
}
