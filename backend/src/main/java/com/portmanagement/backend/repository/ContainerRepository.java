package com.portmanagement.backend.repository;

import com.portmanagement.backend.entity.Container;
import com.portmanagement.backend.entity.ContainerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Long> {

    Optional<Container> findByContainerId(String containerId);

    boolean existsByContainerId(String containerId);

    List<Container> findByStatus(ContainerStatus status);

    List<Container> findByAssignedVesselId(Long vesselId);
}
