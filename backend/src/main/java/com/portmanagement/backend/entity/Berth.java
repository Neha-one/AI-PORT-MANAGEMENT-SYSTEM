package com.portmanagement.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entity representing a Port Berth dock location.
 */
@Entity
@Table(name = "berths")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Berth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "berth_id", unique = true, nullable = false)
    private String berthId;

    @Column(name = "berth_name", nullable = false)
    private String berthName;

    @Column(name = "capacity_length", nullable = false)
    private Double capacityLength;

    @Column(name = "capacity_depth", nullable = false)
    private Double capacityDepth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private BerthStatus status = BerthStatus.AVAILABLE;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
