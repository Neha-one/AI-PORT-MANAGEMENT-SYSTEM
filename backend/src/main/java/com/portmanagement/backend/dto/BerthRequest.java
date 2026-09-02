package com.portmanagement.backend.dto;

import com.portmanagement.backend.entity.BerthStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BerthRequest {

    @NotBlank(message = "Berth ID is required")
    private String berthId;

    @NotBlank(message = "Berth name is required")
    private String berthName;

    @NotNull(message = "Capacity length is required")
    @Positive(message = "Capacity length must be positive")
    private Double capacityLength;

    @NotNull(message = "Capacity depth is required")
    @Positive(message = "Capacity depth must be positive")
    private Double capacityDepth;

    private BerthStatus status;
}
