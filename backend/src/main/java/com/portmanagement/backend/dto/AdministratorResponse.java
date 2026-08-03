package com.portmanagement.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdministratorResponse {

    private Long id;
    private String fullName;
    private String employeeId;
    private String email;
    private String phoneNumber;
    private String designation;
    private String department;
    private String role;
    private boolean active;
}
