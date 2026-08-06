package com.portmanagement.backend.service.impl;

import com.portmanagement.backend.dto.*;
import com.portmanagement.backend.entity.Berth;
import com.portmanagement.backend.entity.BerthStatus;
import com.portmanagement.backend.entity.Vessel;
import com.portmanagement.backend.entity.VesselStatus;
import com.portmanagement.backend.repository.BerthRepository;
import com.portmanagement.backend.repository.VesselRepository;
import com.portmanagement.backend.service.VesselService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of VesselService for Ship/Vessel scheduling and berth allocation.
 */
@Service
@RequiredArgsConstructor
public class VesselServiceImpl implements VesselService {

    private final VesselRepository vesselRepository;
    private final BerthRepository berthRepository;

    @Override
    @Transactional(readOnly = true)
    public List<VesselResponse> getAllVessels(VesselStatus status) {
        List<Vessel> vessels = (status != null)
                ? vesselRepository.findByStatus(status)
                : vesselRepository.findAll();
        return vessels.stream()
                .map(VesselResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VesselResponse getVesselById(Long id) {
        Vessel vessel = findVesselEntityById(id);
        return VesselResponse.fromEntity(vessel);
    }

    @Override
    @Transactional
    public VesselResponse registerVessel(VesselRequest request) {
        if (vesselRepository.existsByVesselId(request.getVesselId())) {
            throw new IllegalArgumentException("Vessel with ID '" + request.getVesselId() + "' already registered.");
        }

        Berth assignedBerth = null;
        if (request.getAssignedBerthId() != null) {
            assignedBerth = berthRepository.findById(request.getAssignedBerthId())
                    .orElseThrow(() -> new IllegalArgumentException("Berth not found with ID: " + request.getAssignedBerthId()));
            validateBerthCapacity(assignedBerth, request.getLength(), request.getDraftDepth());
        }

        Vessel vessel = Vessel.builder()
                .vesselId(request.getVesselId())
                .vesselName(request.getVesselName())
                .shipType(request.getShipType())
                .length(request.getLength())
                .draftDepth(request.getDraftDepth())
                .arrivalEta(request.getArrivalEta())
                .departureEtd(request.getDepartureEtd())
                .status(request.getStatus() != null ? request.getStatus() : VesselStatus.APPROACHING)
                .assignedBerth(assignedBerth)
                .build();

        if (assignedBerth != null && vessel.getStatus() == VesselStatus.DOCKED) {
            assignedBerth.setStatus(BerthStatus.OCCUPIED);
            berthRepository.save(assignedBerth);
        }

        Vessel saved = vesselRepository.save(vessel);
        return VesselResponse.fromEntity(saved);
    }

    @Override
    @Transactional
    public VesselResponse assignBerth(Long vesselId, AssignBerthRequest request) {
        Vessel vessel = findVesselEntityById(vesselId);
        Berth newBerth = berthRepository.findById(request.getBerthId())
                .orElseThrow(() -> new IllegalArgumentException("Berth not found with ID: " + request.getBerthId()));

        if (newBerth.getStatus() == BerthStatus.MAINTENANCE) {
            throw new IllegalArgumentException("Berth '" + newBerth.getBerthName() + "' is currently under maintenance.");
        }

        if (newBerth.getStatus() == BerthStatus.OCCUPIED && !newBerth.equals(vessel.getAssignedBerth())) {
            throw new IllegalArgumentException("Berth '" + newBerth.getBerthName() + "' is already occupied by another ship.");
        }

        // Validate physical dimensions
        validateBerthCapacity(newBerth, vessel.getLength(), vessel.getDraftDepth());

        // Release old berth if changing berth
        if (vessel.getAssignedBerth() != null && !vessel.getAssignedBerth().equals(newBerth)) {
            Berth oldBerth = vessel.getAssignedBerth();
            oldBerth.setStatus(BerthStatus.AVAILABLE);
            berthRepository.save(oldBerth);
        }

        // Assign new berth
        vessel.setAssignedBerth(newBerth);
        vessel.setStatus(VesselStatus.DOCKED);

        newBerth.setStatus(BerthStatus.OCCUPIED);
        berthRepository.save(newBerth);

        Vessel updated = vesselRepository.save(vessel);
        return VesselResponse.fromEntity(updated);
    }

    @Override
    @Transactional
    public VesselResponse updateSchedule(Long vesselId, VesselScheduleUpdateRequest request) {
        Vessel vessel = findVesselEntityById(vesselId);

        vessel.setArrivalEta(request.getArrivalEta());
        if (request.getDepartureEtd() != null) {
            vessel.setDepartureEtd(request.getDepartureEtd());
        }
        if (request.getStatus() != null) {
            vessel.setStatus(request.getStatus());

            // If vessel departed, free up berth
            if (request.getStatus() == VesselStatus.DEPARTED && vessel.getAssignedBerth() != null) {
                Berth berth = vessel.getAssignedBerth();
                berth.setStatus(BerthStatus.AVAILABLE);
                berthRepository.save(berth);
            }
        }

        Vessel updated = vesselRepository.save(vessel);
        return VesselResponse.fromEntity(updated);
    }

    @Override
    @Transactional
    public VesselResponse departVessel(Long vesselId) {
        Vessel vessel = findVesselEntityById(vesselId);

        if (vessel.getAssignedBerth() != null) {
            Berth berth = vessel.getAssignedBerth();
            berth.setStatus(BerthStatus.AVAILABLE);
            berthRepository.save(berth);
        }

        vessel.setStatus(VesselStatus.DEPARTED);
        Vessel updated = vesselRepository.save(vessel);
        return VesselResponse.fromEntity(updated);
    }

    @Override
    @Transactional
    public VesselResponse updateVessel(Long id, VesselRequest request) {
        Vessel vessel = findVesselEntityById(id);

        if (!vessel.getVesselId().equals(request.getVesselId()) && vesselRepository.existsByVesselId(request.getVesselId())) {
            throw new IllegalArgumentException("Vessel with ID '" + request.getVesselId() + "' already exists.");
        }

        vessel.setVesselId(request.getVesselId());
        vessel.setVesselName(request.getVesselName());
        vessel.setShipType(request.getShipType());
        vessel.setLength(request.getLength());
        vessel.setDraftDepth(request.getDraftDepth());
        vessel.setArrivalEta(request.getArrivalEta());
        if (request.getDepartureEtd() != null) {
            vessel.setDepartureEtd(request.getDepartureEtd());
        }
        if (request.getStatus() != null) {
            vessel.setStatus(request.getStatus());
        }

        Vessel updated = vesselRepository.save(vessel);
        return VesselResponse.fromEntity(updated);
    }

    @Override
    @Transactional
    public void deleteVessel(Long id) {
        Vessel vessel = findVesselEntityById(id);
        if (vessel.getAssignedBerth() != null && vessel.getStatus() == VesselStatus.DOCKED) {
            Berth berth = vessel.getAssignedBerth();
            berth.setStatus(BerthStatus.AVAILABLE);
            berthRepository.save(berth);
        }
        vesselRepository.delete(vessel);
    }

    @Override
    public Vessel findVesselEntityById(Long id) {
        return vesselRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vessel not found with ID: " + id));
    }

    private void validateBerthCapacity(Berth berth, Double vesselLength, Double vesselDraftDepth) {
        if (berth.getCapacityLength() < vesselLength) {
            throw new IllegalArgumentException(String.format(
                    "Berth length capacity (%.2fm) is insufficient for vessel length (%.2fm).",
                    berth.getCapacityLength(), vesselLength));
        }
        if (berth.getCapacityDepth() < vesselDraftDepth) {
            throw new IllegalArgumentException(String.format(
                    "Berth depth capacity (%.2fm) is insufficient for vessel draft depth (%.2fm).",
                    berth.getCapacityDepth(), vesselDraftDepth));
        }
    }
}
