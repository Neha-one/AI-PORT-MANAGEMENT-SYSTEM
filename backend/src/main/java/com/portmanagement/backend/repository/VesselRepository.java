package com.portmanagement.backend.repository;

import com.portmanagement.backend.entity.Vessel;
import com.portmanagement.backend.entity.VesselStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VesselRepository extends JpaRepository<Vessel, Long> {

    Optional<Vessel> findByVesselId(String vesselId);

    boolean existsByVesselId(String vesselId);

    List<Vessel> findByStatus(VesselStatus status);

    List<Vessel> findByAssignedBerthId(Long berthId);
}
