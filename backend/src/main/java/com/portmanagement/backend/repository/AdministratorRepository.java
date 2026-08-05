package com.portmanagement.backend.repository;

import com.portmanagement.backend.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    Optional<Administrator> findByEmail(String email);

    Optional<Administrator> findByEmployeeId(String employeeId);

<<<<<<< HEAD
=======
    Optional<Administrator> findByEmailIgnoreCaseOrEmployeeIdIgnoreCase(String email, String employeeId);

>>>>>>> ankit
    boolean existsByEmail(String email);

    boolean existsByEmployeeId(String employeeId);

    Optional<Administrator> findTopByOrderByIdDesc();
}
