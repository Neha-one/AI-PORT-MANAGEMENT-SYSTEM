package com.portmanagement.backend.service;

import com.portmanagement.backend.dto.*;
import com.portmanagement.backend.entity.Vessel;
import com.portmanagement.backend.entity.VesselStatus;

import java.util.List;

/**
 * Interface defining operations for Ship/Vessel Management & Scheduling.
 */
public interface VesselService {

    List<VesselResponse> getAllVessels(VesselStatus status);

    VesselResponse getVesselById(Long id);

    VesselResponse registerVessel(VesselRequest request);

    VesselResponse updateVessel(Long id, VesselRequest request);

    VesselResponse assignBerth(Long vesselId, AssignBerthRequest request);

    VesselResponse updateSchedule(Long vesselId, VesselScheduleUpdateRequest request);

    VesselResponse departVessel(Long vesselId);

    void deleteVessel(Long id);

    Vessel findVesselEntityById(Long id);
}
