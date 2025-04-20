package com.hotel.tracking.repository;

import com.hotel.tracking.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelRepo extends JpaRepository<Parcel, Long> {

    @Query("SELECT p FROM Parcel p WHERE p.guest.id = :guestId AND p.isPickedUp = false")
    List<Parcel> findByGuestIdAndPickedUpFalse(Long guestId);
}
