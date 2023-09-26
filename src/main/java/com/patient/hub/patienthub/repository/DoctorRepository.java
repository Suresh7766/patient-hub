package com.patient.hub.patienthub.repository;

import com.patient.hub.patienthub.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
