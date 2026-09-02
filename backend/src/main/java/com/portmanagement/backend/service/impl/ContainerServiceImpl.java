package com.portmanagement.backend.service.impl;

import com.portmanagement.backend.dto.*;
import com.portmanagement.backend.entity.Container;
import com.portmanagement.backend.entity.ContainerStatus;
import com.portmanagement.backend.entity.Vessel;
import com.portmanagement.backend.repository.ContainerRepository;
import com.portmanagement.backend.repository.VesselRepository;
import com.portmanagement.backend.service.ContainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of ContainerService for container logs, yard locations, and vessel assignments.
 */
@Service
@RequiredArgsConstructor
public class ContainerServiceImpl implements ContainerService {

    private final ContainerRepository containerRepository;
    private final VesselRepository vesselRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ContainerResponse> getAllContainers(ContainerStatus status) {
        List<Container> containers = (status != null)
                ? containerRepository.findByStatus(status)
                : containerRepository.findAll();
        return containers.stream()
                .map(ContainerResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ContainerResponse getContainerById(Long id) {
        Container container = findContainerEntityById(id);
        return ContainerResponse.fromEntity(container);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContainerResponse> getContainersByVessel(Long vesselId) {
        return containerRepository.findByAssignedVesselId(vesselId).stream()
                .map(ContainerResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ContainerResponse logContainerEntry(ContainerRequest request) {
        if (containerRepository.existsByContainerId(request.getContainerId())) {
            throw new IllegalArgumentException("Container with ID '" + request.getContainerId() + "' already exists.");
        }

        Vessel assignedVessel = null;
        if (request.getAssignedVesselId() != null) {
            assignedVessel = vesselRepository.findById(request.getAssignedVesselId())
                    .orElseThrow(() -> new IllegalArgumentException("Vessel not found with ID: " + request.getAssignedVesselId()));
        }

        Container container = Container.builder()
                .containerId(request.getContainerId())
                .weight(request.getWeight())
                .cargoType(request.getCargoType())
                .assignedVessel(assignedVessel)
                .assignedYardLocation(request.getAssignedYardLocation())
                .status(request.getStatus() != null ? request.getStatus() : ContainerStatus.IN_TRANSIT)
                .build();

        Container saved = containerRepository.save(container);
        return ContainerResponse.fromEntity(saved);
    }

    @Override
    @Transactional
    public ContainerResponse assignYardLocation(Long containerId, AssignLocationRequest request) {
        Container container = findContainerEntityById(containerId);
        container.setAssignedYardLocation(request.getAssignedYardLocation());

        Container updated = containerRepository.save(container);
        return ContainerResponse.fromEntity(updated);
    }

    @Override
    @Transactional
    public ContainerResponse assignVessel(Long containerId, Long vesselId) {
        Container container = findContainerEntityById(containerId);
        Vessel vessel = vesselRepository.findById(vesselId)
                .orElseThrow(() -> new IllegalArgumentException("Vessel not found with ID: " + vesselId));

        container.setAssignedVessel(vessel);

        Container updated = containerRepository.save(container);
        return ContainerResponse.fromEntity(updated);
    }

    @Override
    @Transactional
    public ContainerResponse updateContainerStatus(Long containerId, ContainerStatusUpdateRequest request) {
        Container container = findContainerEntityById(containerId);
        container.setStatus(request.getStatus());

        Container updated = containerRepository.save(container);
        return ContainerResponse.fromEntity(updated);
    }

    @Override
    @Transactional
    public ContainerResponse updateContainer(Long id, ContainerRequest request) {
        Container container = findContainerEntityById(id);

        if (!container.getContainerId().equals(request.getContainerId()) && containerRepository.existsByContainerId(request.getContainerId())) {
            throw new IllegalArgumentException("Container with ID '" + request.getContainerId() + "' already exists.");
        }

        container.setContainerId(request.getContainerId());
        container.setWeight(request.getWeight());
        container.setCargoType(request.getCargoType());
        container.setAssignedYardLocation(request.getAssignedYardLocation());

        if (request.getAssignedVesselId() != null) {
            Vessel vessel = vesselRepository.findById(request.getAssignedVesselId())
                    .orElseThrow(() -> new IllegalArgumentException("Vessel not found with ID: " + request.getAssignedVesselId()));
            container.setAssignedVessel(vessel);
        } else {
            container.setAssignedVessel(null);
        }

        if (request.getStatus() != null) {
            container.setStatus(request.getStatus());
        }

        Container updated = containerRepository.save(container);
        return ContainerResponse.fromEntity(updated);
    }

    @Override
    @Transactional
    public void deleteContainer(Long id) {
        Container container = findContainerEntityById(id);
        containerRepository.delete(container);
    }

    @Override
    public Container findContainerEntityById(Long id) {
        return containerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Container not found with ID: " + id));
    }
}
