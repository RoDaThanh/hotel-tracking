package com.hotel.tracking.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cardId;

    private boolean isCheckedIn;

    private boolean isCheckedOut;

    private LocalDateTime checkedInDateTime;

    private LocalDateTime checkedOutDateTime;
}
