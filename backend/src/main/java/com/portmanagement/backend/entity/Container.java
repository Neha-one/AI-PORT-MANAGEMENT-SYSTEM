package com.portmanagement.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entity representing a freight Container within the port terminal or assigned to a vessel.
 */
@Entity
@Table(name = "containers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "container_id", unique = true, nullable = false)
    private String containerId;

    @Column(nullable = false)
    private Double weight;

    @Column(name = "cargo_type", nullable = false)
    private String cargoType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_vessel_id")
    private Vessel assignedVessel;

    @Column(name = "assigned_yard_location")
    private String assignedYardLocation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private ContainerStatus status = ContainerStatus.IN_TRANSIT;

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
