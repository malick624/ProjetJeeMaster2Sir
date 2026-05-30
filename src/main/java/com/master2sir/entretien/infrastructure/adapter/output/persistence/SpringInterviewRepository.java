package com.master2sir.entretien.infrastructure.adapter.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringInterviewRepository extends JpaRepository<InterviewEntity, UUID> {
}
