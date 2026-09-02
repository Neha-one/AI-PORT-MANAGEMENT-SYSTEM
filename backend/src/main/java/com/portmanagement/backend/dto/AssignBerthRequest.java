package com.portmanagement.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignBerthRequest {

    @NotNull(message = "Berth ID is required")
    private Long berthId;
}
