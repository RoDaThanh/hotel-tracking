package com.hotel.tracking.service;

import com.hotel.tracking.model.Parcel;
import com.hotel.tracking.repository.ParcelRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelService {
    private final ParcelRepo parcelRepo;

    public ParcelService(ParcelRepo parcelRepo) {
        this.parcelRepo = parcelRepo;
    }

    public List<Parcel> findPendingParcel(Long guestId) {
        return parcelRepo.findByGuestIdAndPickedUpFalse(guestId);
    }



}
