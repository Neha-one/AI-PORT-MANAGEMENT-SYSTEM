package com.portmanagement.backend.controller;

import com.portmanagement.backend.dto.*;
import com.portmanagement.backend.entity.ContainerStatus;
import com.portmanagement.backend.service.ContainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Container Management APIs.
 */
@RestController
@RequestMapping("/api/containers")
@RequiredArgsConstructor
@Tag(name = "Container Management", description = "APIs for logging containers, yard block locations, and vessel loading")
public class ContainerController {

    private final ContainerService containerService;

    @GetMapping
    @Operation(summary = "Get all containers", description = "Retrieves all containers with optional status filtering")
    public ResponseEntity<ApiResponse<List<ContainerResponse>>> getAllContainers(
            @RequestParam(required = false) ContainerStatus status) {
        List<ContainerResponse> containers = containerService.getAllContainers(status);
        return ResponseEntity.ok(ApiResponse.success("Containers retrieved successfully", containers));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get container by ID", description = "Retrieves details of a specific container")
    public ResponseEntity<ApiResponse<ContainerResponse>> getContainerById(@PathVariable Long id) {
        ContainerResponse container = containerService.getContainerById(id);
        return ResponseEntity.ok(ApiResponse.success("Container retrieved successfully", container));
    }

    @GetMapping("/vessel/{vesselId}")
    @Operation(summary = "Get containers by vessel", description = "Retrieves containers assigned to a specific vessel")
    public ResponseEntity<ApiResponse<List<ContainerResponse>>> getContainersByVessel(@PathVariable Long vesselId) {
        List<ContainerResponse> containers = containerService.getContainersByVessel(vesselId);
        return ResponseEntity.ok(ApiResponse.success("Vessel containers retrieved successfully", containers));
    }

    @PostMapping
    @Operation(summary = "Log container entry", description = "Logs a new container arrival entry into the terminal")
    public ResponseEntity<ApiResponse<ContainerResponse>> logContainerEntry(@Valid @RequestBody ContainerRequest request) {
        ContainerResponse created = containerService.logContainerEntry(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Container logged successfully", created));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update container details", description = "Updates details of an existing container")
    public ResponseEntity<ApiResponse<ContainerResponse>> updateContainer(
            @PathVariable Long id,
            @Valid @RequestBody ContainerRequest request) {
        ContainerResponse updated = containerService.updateContainer(id, request);
        return ResponseEntity.ok(ApiResponse.success("Container updated successfully", updated));
    }

    @PutMapping("/{id}/assign-location")
    @Operation(summary = "Assign yard location", description = "Assigns or updates yard block/row/bay location for a container")
    public ResponseEntity<ApiResponse<ContainerResponse>> assignYardLocation(
            @PathVariable Long id,
            @Valid @RequestBody AssignLocationRequest request) {
        ContainerResponse updated = containerService.assignYardLocation(id, request);
        return ResponseEntity.ok(ApiResponse.success("Yard location assigned successfully", updated));
    }

    @PutMapping("/{id}/assign-vessel/{vesselId}")
    @Operation(summary = "Assign container to vessel", description = "Associates a container with a vessel")
    public ResponseEntity<ApiResponse<ContainerResponse>> assignVessel(
            @PathVariable Long id,
            @PathVariable Long vesselId) {
        ContainerResponse updated = containerService.assignVessel(id, vesselId);
        return ResponseEntity.ok(ApiResponse.success("Container assigned to vessel successfully", updated));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update container status", description = "Updates container status (IN_TRANSIT, LOADED, UNLOADED)")
    public ResponseEntity<ApiResponse<ContainerResponse>> updateContainerStatus(
            @PathVariable Long id,
            @Valid @RequestBody ContainerStatusUpdateRequest request) {
        ContainerResponse updated = containerService.updateContainerStatus(id, request);
        return ResponseEntity.ok(ApiResponse.success("Container status updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete container", description = "Removes a container record from the terminal system")
    public ResponseEntity<ApiResponse<Void>> deleteContainer(@PathVariable Long id) {
        containerService.deleteContainer(id);
        return ResponseEntity.ok(ApiResponse.success("Container deleted successfully", null));
    }
}
