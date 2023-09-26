package com.patient.hub.patienthub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "DOCTOR")
@Setter
@Getter
@Audited
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String fullName;

    private String mobileNumber;

    private String specialist;

    @OneToMany(mappedBy = "doctor")
    @NotAudited
    private List<BookingSlot> bookingSlots;

    @CreationTimestamp
    private LocalDateTime createdTimeStamp;

    @UpdateTimestamp
    private LocalDateTime updatedTimeStamp;
}
