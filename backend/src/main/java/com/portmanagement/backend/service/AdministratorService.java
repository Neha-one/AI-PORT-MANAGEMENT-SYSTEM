package com.portmanagement.backend.service;

import com.portmanagement.backend.dto.AdministratorResponse;
import com.portmanagement.backend.dto.AdministratorLoginRequest;
import com.portmanagement.backend.dto.AdministratorSignupRequest;
import com.portmanagement.backend.entity.Administrator;
import jakarta.servlet.http.HttpServletRequest;

public interface AdministratorService {

    AdministratorResponse registerAdministrator(AdministratorSignupRequest request);

    AdministratorResponse loginAdministrator(AdministratorLoginRequest request, HttpServletRequest request);

    void logoutAdministrator(HttpServletRequest request);
}
