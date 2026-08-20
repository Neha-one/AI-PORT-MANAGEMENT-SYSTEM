package com.portmanagement.backend.service;

import com.portmanagement.backend.dto.AdministratorLoginRequest;
import com.portmanagement.backend.dto.AdministratorResponse;
import com.portmanagement.backend.dto.AdministratorSignupRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface AdministratorService {
    
    AdministratorResponse registerAdministrator(AdministratorSignupRequest request);

    AdministratorResponse loginAdministrator(AdministratorLoginRequest request, HttpServletRequest httpServletRequest);

    void logoutAdministrator(HttpServletRequest request);
}
