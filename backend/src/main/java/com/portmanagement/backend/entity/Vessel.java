package com.portmanagement.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entity representing a Ship / Vessel arriving or docked at the port.
 */
@Entity
@Table(name = "vessels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vessel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vessel_id", unique = true, nullable = false)
    private String vesselId;

    @Column(name = "vessel_name", nullable = false)
    private String vesselName;

    @Column(name = "ship_type", nullable = false)
    private String shipType;

    @Column(nullable = false)
    private Double length;

    @Column(name = "draft_depth", nullable = false)
    private Double draftDepth;

    @Column(name = "arrival_eta", nullable = false)
    private LocalDateTime arrivalEta;

    @Column(name = "departure_etd")
    private LocalDateTime departureEtd;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private VesselStatus status = VesselStatus.APPROACHING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_berth_id")
    private Berth assignedBerth;

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
