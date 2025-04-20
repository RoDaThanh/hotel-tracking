package com.hotel.tracking.service;

import com.hotel.tracking.model.Parcel;
import com.hotel.tracking.repository.ParcelRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ParCelServiceTest {
    private ParcelRepo parcelRepo;
    private ParcelService parcelService;

    @BeforeEach
    void setUp() {
        parcelRepo = mock(ParcelRepo.class);
        parcelService = new ParcelService(parcelRepo);
    }

    @Test
    void findPendingParcel_shouldReturnPendingParcels_whenFound() {
        // setup
        Long guestId = 1L;
        List<Parcel> expectedParcels = List.of(new Parcel(), new Parcel());
        when(parcelRepo.findByGuestIdAndPickedUpFalse(guestId)).thenReturn(expectedParcels);

        // execute
        List<Parcel> actualParcels = parcelService.findPendingParcel(guestId);

        // assert
        assertEquals(expectedParcels, actualParcels);
        verify(parcelRepo, times(1)).findByGuestIdAndPickedUpFalse(guestId);
    }

    @Test
    void findPendingParcel_shouldReturnPendingParcels_whenNotFound() {
        // setup
        Long guestId = 1L;
        when(parcelRepo.findByGuestIdAndPickedUpFalse(guestId)).thenReturn(new ArrayList<>());

        // execute
        List<Parcel> actualParcels = parcelService.findPendingParcel(guestId);

        // assert
        assertEquals(0, actualParcels.size());
        verify(parcelRepo, times(1)).findByGuestIdAndPickedUpFalse(guestId);
    }
}
