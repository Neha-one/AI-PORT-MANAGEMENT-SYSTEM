package com.portmanagement.backend.service;

import com.portmanagement.backend.dto.AdministratorResponse;
import com.portmanagement.backend.dto.AdministratorSignupRequest;

public interface AdministratorService {
    
    AdministratorResponse registerAdministrator(AdministratorSignupRequest request);
}
