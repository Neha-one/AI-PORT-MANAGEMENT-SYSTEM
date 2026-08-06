package com.portmanagement.backend.repository;

import com.portmanagement.backend.entity.Berth;
import com.portmanagement.backend.entity.BerthStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BerthRepository extends JpaRepository<Berth, Long> {

    Optional<Berth> findByBerthId(String berthId);

    boolean existsByBerthId(String berthId);

    List<Berth> findByStatus(BerthStatus status);
}
