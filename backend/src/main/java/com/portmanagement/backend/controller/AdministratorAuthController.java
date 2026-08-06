package com.portmanagement.backend.controller;

import com.portmanagement.backend.dto.ApiResponse;
<<<<<<< HEAD
<<<<<<< HEAD
import com.portmanagement.backend.dto.AdministratorResponse;

import com.portmanagement.backend.dto.AdministratorLoginRequest;

=======
import com.portmanagement.backend.dto.AdministratorLoginRequest;
import com.portmanagement.backend.dto.AdministratorResponse;
>>>>>>> 5619659 (error fixed)
=======
import com.portmanagement.backend.dto.AdministratorLoginRequest;
import com.portmanagement.backend.dto.AdministratorResponse;
>>>>>>> 8755a78035839be5230e92263fb02d716989b4a3
import com.portmanagement.backend.dto.AdministratorSignupRequest;
import com.portmanagement.backend.service.AdministratorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
<<<<<<< HEAD

import jakarta.servlet.http.HttpServletRequest;


=======
>>>>>>> 5619659 (error fixed)
=======
>>>>>>> 8755a78035839be5230e92263fb02d716989b4a3
@RestController
@RequestMapping("/auth/admin/port-management")
@RequiredArgsConstructor
public class AdministratorAuthController {

    private final AdministratorService administratorService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AdministratorResponse>> signup(
            @Valid @RequestBody AdministratorSignupRequest request) {

        AdministratorResponse response = administratorService.registerAdministrator(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Administrator account registered successfully with ADMIN role",
                        response));
    }
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> 5619659 (error fixed)
=======
>>>>>>> 8755a78035839be5230e92263fb02d716989b4a3

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AdministratorResponse>> login(
            @Valid @RequestBody AdministratorLoginRequest request,
            HttpServletRequest httpServletRequest) {

        AdministratorResponse response =
                administratorService.loginAdministrator(request, httpServletRequest);

        return ResponseEntity.ok(
                ApiResponse.success("Administrator logged in successfully", response));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpServletRequest request) {

        administratorService.logoutAdministrator(request);

        return ResponseEntity.ok(
                ApiResponse.success("Administrator logged out successfully", null));
    }
<<<<<<< HEAD
<<<<<<< HEAD

}
=======
}
>>>>>>> 5619659 (error fixed)
=======
}
>>>>>>> 8755a78035839be5230e92263fb02d716989b4a3
