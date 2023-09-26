package com.patient.hub.patienthub.repository;

import com.patient.hub.patienthub.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
