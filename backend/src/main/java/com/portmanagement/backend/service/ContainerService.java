package com.portmanagement.backend.service;

import com.portmanagement.backend.dto.*;
import com.portmanagement.backend.entity.Container;
import com.portmanagement.backend.entity.ContainerStatus;

import java.util.List;

/**
 * Interface defining operations for Container Management.
 */
public interface ContainerService {

    List<ContainerResponse> getAllContainers(ContainerStatus status);

    ContainerResponse getContainerById(Long id);

    List<ContainerResponse> getContainersByVessel(Long vesselId);

    ContainerResponse logContainerEntry(ContainerRequest request);

    ContainerResponse assignYardLocation(Long containerId, AssignLocationRequest request);

    ContainerResponse assignVessel(Long containerId, Long vesselId);

    ContainerResponse updateContainerStatus(Long containerId, ContainerStatusUpdateRequest request);

    ContainerResponse updateContainer(Long id, ContainerRequest request);

    void deleteContainer(Long id);

    Container findContainerEntityById(Long id);
}
