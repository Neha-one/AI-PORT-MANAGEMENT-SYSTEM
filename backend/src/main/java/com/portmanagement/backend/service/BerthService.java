package com.portmanagement.backend.service;

import com.portmanagement.backend.dto.BerthRequest;
import com.portmanagement.backend.dto.BerthResponse;
import com.portmanagement.backend.dto.BerthStatusUpdateRequest;
import com.portmanagement.backend.entity.Berth;
import com.portmanagement.backend.entity.BerthStatus;

import java.util.List;

/**
 * Interface defining operations for Berth Management.
 */
public interface BerthService {

    List<BerthResponse> getAllBerths(BerthStatus status);

    BerthResponse getBerthById(Long id);

    BerthResponse createBerth(BerthRequest request);

    BerthResponse updateBerth(Long id, BerthRequest request);

    BerthResponse updateBerthStatus(Long id, BerthStatusUpdateRequest request);

    void deleteBerth(Long id);

    Berth findBerthEntityById(Long id);
}
