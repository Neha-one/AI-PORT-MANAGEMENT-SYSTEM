package com.portmanagement.backend.controller;

import com.portmanagement.backend.dto.*;
import com.portmanagement.backend.entity.VesselStatus;
import com.portmanagement.backend.service.VesselService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Ship / Vessel Registration and Scheduling APIs.
 */
@RestController
@RequestMapping("/api/vessels")
@RequiredArgsConstructor
@Tag(name = "Ship & Vessel Scheduling", description = "APIs for vessel registration, berth assignments, and ETA/ETD schedules")
public class VesselController {

    private final VesselService vesselService;

    @GetMapping
    @Operation(summary = "Get all vessels", description = "Retrieves all vessels with optional status filtering")
    public ResponseEntity<ApiResponse<List<VesselResponse>>> getAllVessels(
            @RequestParam(required = false) VesselStatus status) {
        List<VesselResponse> vessels = vesselService.getAllVessels(status);
        return ResponseEntity.ok(ApiResponse.success("Vessels retrieved successfully", vessels));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vessel by ID", description = "Retrieves details of a specific vessel")
    public ResponseEntity<ApiResponse<VesselResponse>> getVesselById(@PathVariable Long id) {
        VesselResponse vessel = vesselService.getVesselById(id);
        return ResponseEntity.ok(ApiResponse.success("Vessel retrieved successfully", vessel));
    }

    @PostMapping
    @Operation(summary = "Register arriving ship", description = "Registers a new arriving ship/vessel entry")
    public ResponseEntity<ApiResponse<VesselResponse>> registerVessel(@Valid @RequestBody VesselRequest request) {
        VesselResponse registered = vesselService.registerVessel(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Vessel registered successfully", registered));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update vessel details", description = "Updates information for an existing vessel")
    public ResponseEntity<ApiResponse<VesselResponse>> updateVessel(
            @PathVariable Long id,
            @Valid @RequestBody VesselRequest request) {
        VesselResponse updated = vesselService.updateVessel(id, request);
        return ResponseEntity.ok(ApiResponse.success("Vessel updated successfully", updated));
    }

    @PutMapping("/{id}/assign-berth")
    @Operation(summary = "Assign berth to ship", description = "Assigns an available berth to a ship with depth/length validation")
    public ResponseEntity<ApiResponse<VesselResponse>> assignBerth(
            @PathVariable Long id,
            @Valid @RequestBody AssignBerthRequest request) {
        VesselResponse updated = vesselService.assignBerth(id, request);
        return ResponseEntity.ok(ApiResponse.success("Berth assigned to vessel successfully", updated));
    }

    @PutMapping("/{id}/schedule")
    @Operation(summary = "Update vessel schedule", description = "Updates arrival ETA, departure ETD, and navigation status")
    public ResponseEntity<ApiResponse<VesselResponse>> updateSchedule(
            @PathVariable Long id,
            @Valid @RequestBody VesselScheduleUpdateRequest request) {
        VesselResponse updated = vesselService.updateSchedule(id, request);
        return ResponseEntity.ok(ApiResponse.success("Vessel schedule updated successfully", updated));
    }

    @PutMapping("/{id}/depart")
    @Operation(summary = "Depart vessel", description = "Marks ship as DEPARTED and frees its assigned berth")
    public ResponseEntity<ApiResponse<VesselResponse>> departVessel(@PathVariable Long id) {
        VesselResponse updated = vesselService.departVessel(id);
        return ResponseEntity.ok(ApiResponse.success("Vessel departed and berth released", updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete vessel", description = "Removes a vessel entry from the database")
    public ResponseEntity<ApiResponse<Void>> deleteVessel(@PathVariable Long id) {
        vesselService.deleteVessel(id);
        return ResponseEntity.ok(ApiResponse.success("Vessel deleted successfully", null));
    }
}
