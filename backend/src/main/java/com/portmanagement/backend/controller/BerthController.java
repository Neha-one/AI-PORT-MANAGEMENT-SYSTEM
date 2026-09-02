package com.portmanagement.backend.controller;

import com.portmanagement.backend.dto.ApiResponse;
import com.portmanagement.backend.dto.BerthRequest;
import com.portmanagement.backend.dto.BerthResponse;
import com.portmanagement.backend.dto.BerthStatusUpdateRequest;
import com.portmanagement.backend.entity.BerthStatus;
import com.portmanagement.backend.service.BerthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Berth Management APIs.
 */
@RestController
@RequestMapping("/api/berths")
@RequiredArgsConstructor
@Tag(name = "Berth Management", description = "APIs for managing port docks and berths")
public class BerthController {

    private final BerthService berthService;

    @GetMapping
    @Operation(summary = "Get all berths", description = "Retrieves all berths with an optional status filter")
    public ResponseEntity<ApiResponse<List<BerthResponse>>> getAllBerths(
            @RequestParam(required = false) BerthStatus status) {
        List<BerthResponse> berths = berthService.getAllBerths(status);
        return ResponseEntity.ok(ApiResponse.success("Berths retrieved successfully", berths));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get berth by ID", description = "Retrieves details of a specific berth by ID")
    public ResponseEntity<ApiResponse<BerthResponse>> getBerthById(@PathVariable Long id) {
        BerthResponse berth = berthService.getBerthById(id);
        return ResponseEntity.ok(ApiResponse.success("Berth retrieved successfully", berth));
    }

    @PostMapping
    @Operation(summary = "Add new berth", description = "Creates a new berth dock in the port")
    public ResponseEntity<ApiResponse<BerthResponse>> createBerth(@Valid @RequestBody BerthRequest request) {
        BerthResponse created = berthService.createBerth(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Berth created successfully", created));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update berth", description = "Updates details of an existing berth")
    public ResponseEntity<ApiResponse<BerthResponse>> updateBerth(
            @PathVariable Long id,
            @Valid @RequestBody BerthRequest request) {
        BerthResponse updated = berthService.updateBerth(id, request);
        return ResponseEntity.ok(ApiResponse.success("Berth updated successfully", updated));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update berth status", description = "Updates status (AVAILABLE, OCCUPIED, MAINTENANCE)")
    public ResponseEntity<ApiResponse<BerthResponse>> updateBerthStatus(
            @PathVariable Long id,
            @Valid @RequestBody BerthStatusUpdateRequest request) {
        BerthResponse updated = berthService.updateBerthStatus(id, request);
        return ResponseEntity.ok(ApiResponse.success("Berth status updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete berth", description = "Removes a berth dock from the system")
    public ResponseEntity<ApiResponse<Void>> deleteBerth(@PathVariable Long id) {
        berthService.deleteBerth(id);
        return ResponseEntity.ok(ApiResponse.success("Berth deleted successfully", null));
    }
}
