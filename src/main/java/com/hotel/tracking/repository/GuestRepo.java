package com.hotel.tracking.repository;

import com.hotel.tracking.model.Guest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepo extends JpaRepository<Guest, Long> {

    @Query("SELECT g FROM Guest g WHERE g.isCheckedIn = true AND g.isCheckedOut = false")
    Slice<Guest> finCurrentGuests(Pageable pageable);
}
