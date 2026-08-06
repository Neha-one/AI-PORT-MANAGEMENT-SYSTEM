package com.portmanagement.backend.service.impl;

import com.portmanagement.backend.dto.BerthRequest;
import com.portmanagement.backend.dto.BerthResponse;
import com.portmanagement.backend.dto.BerthStatusUpdateRequest;
import com.portmanagement.backend.entity.Berth;
import com.portmanagement.backend.entity.BerthStatus;
import com.portmanagement.backend.entity.Vessel;
import com.portmanagement.backend.entity.VesselStatus;
import com.portmanagement.backend.repository.BerthRepository;
import com.portmanagement.backend.repository.VesselRepository;
import com.portmanagement.backend.service.BerthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the BerthService interface for managing port berths.
 */
@Service
@RequiredArgsConstructor
public class BerthServiceImpl implements BerthService {

    private final BerthRepository berthRepository;
    private final VesselRepository vesselRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BerthResponse> getAllBerths(BerthStatus status) {
        List<Berth> berths = (status != null)
                ? berthRepository.findByStatus(status)
                : berthRepository.findAll();
        return berths.stream()
                .map(BerthResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BerthResponse getBerthById(Long id) {
        Berth berth = findBerthEntityById(id);
        return BerthResponse.fromEntity(berth);
    }

    @Override
    @Transactional
    public BerthResponse createBerth(BerthRequest request) {
        if (berthRepository.existsByBerthId(request.getBerthId())) {
            throw new IllegalArgumentException("Berth with ID '" + request.getBerthId() + "' already exists.");
        }

        Berth berth = Berth.builder()
                .berthId(request.getBerthId())
                .berthName(request.getBerthName())
                .capacityLength(request.getCapacityLength())
                .capacityDepth(request.getCapacityDepth())
                .status(request.getStatus() != null ? request.getStatus() : BerthStatus.AVAILABLE)
                .build();

        Berth saved = berthRepository.save(berth);
        return BerthResponse.fromEntity(saved);
    }

    @Override
    @Transactional
    public BerthResponse updateBerth(Long id, BerthRequest request) {
        Berth berth = findBerthEntityById(id);

        if (!berth.getBerthId().equals(request.getBerthId()) && berthRepository.existsByBerthId(request.getBerthId())) {
            throw new IllegalArgumentException("Berth with ID '" + request.getBerthId() + "' already exists.");
        }

        berth.setBerthId(request.getBerthId());
        berth.setBerthName(request.getBerthName());
        berth.setCapacityLength(request.getCapacityLength());
        berth.setCapacityDepth(request.getCapacityDepth());
        if (request.getStatus() != null) {
            berth.setStatus(request.getStatus());
        }

        Berth updated = berthRepository.save(berth);
        return BerthResponse.fromEntity(updated);
    }

    @Override
    @Transactional
    public BerthResponse updateBerthStatus(Long id, BerthStatusUpdateRequest request) {
        Berth berth = findBerthEntityById(id);
        berth.setStatus(request.getStatus());
        Berth updated = berthRepository.save(berth);
        return BerthResponse.fromEntity(updated);
    }

    @Override
    @Transactional
    public void deleteBerth(Long id) {
        Berth berth = findBerthEntityById(id);

        List<Vessel> dockedVessels = vesselRepository.findByAssignedBerthId(id);
        boolean hasActiveDockedVessel = dockedVessels.stream()
                .anyMatch(v -> v.getStatus() == VesselStatus.DOCKED || v.getStatus() == VesselStatus.APPROACHING);

        if (hasActiveDockedVessel) {
            throw new IllegalArgumentException("Cannot delete berth '" + berth.getBerthName() + "' because ships are currently assigned/docked at this berth.");
        }

        berthRepository.delete(berth);
    }

    @Override
    public Berth findBerthEntityById(Long id) {
        return berthRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Berth not found with ID: " + id));
    }
}
