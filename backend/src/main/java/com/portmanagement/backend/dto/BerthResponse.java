package com.portmanagement.backend.dto;

import com.portmanagement.backend.entity.Berth;
import com.portmanagement.backend.entity.BerthStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BerthResponse {

    private Long id;
    private String berthId;
    private String berthName;
    private Double capacityLength;
    private Double capacityDepth;
    private BerthStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BerthResponse fromEntity(Berth berth) {
        if (berth == null) return null;
        return BerthResponse.builder()
                .id(berth.getId())
                .berthId(berth.getBerthId())
                .berthName(berth.getBerthName())
                .capacityLength(berth.getCapacityLength())
                .capacityDepth(berth.getCapacityDepth())
                .status(berth.getStatus())
                .createdAt(berth.getCreatedAt())
                .updatedAt(berth.getUpdatedAt())
                .build();
    }
}
