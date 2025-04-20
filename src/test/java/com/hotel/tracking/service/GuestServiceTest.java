package com.hotel.tracking.service;

import com.hotel.tracking.dto.response.PendingParcelsResponse;
import com.hotel.tracking.exception.BadInputPageNumberOrPageSizeException;
import com.hotel.tracking.exception.GuestNotFoundException;
import com.hotel.tracking.model.Guest;
import com.hotel.tracking.model.Parcel;
import com.hotel.tracking.repository.GuestRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GuestServiceTest {

    private GuestRepo guestRepo;
    private GuestService guestService;
    private ParcelService parcelService;

    @BeforeEach
    void setUp() {
        guestRepo = mock(GuestRepo.class);
        parcelService = mock(ParcelService.class);
        guestService = new GuestService(guestRepo, parcelService);
    }

    @Test
    void getPresentGuests_shouldReturnGuests_whenFound() {
        // setup
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Guest guest1 = new Guest();
        guest1.setId(1L);
        guest1.setName("Alice");
        Guest guest2 = new Guest();
        guest2.setId(2L);
        guest2.setName("Bob");
        List<Guest> expectedGuests = Arrays.asList(guest1, guest2);

        Slice<Guest> guestSlice = new SliceImpl<>(expectedGuests, pageable, false);
        when(guestRepo.finCurrentGuests(pageable)).thenReturn(guestSlice);

        // execute
        List<Guest> actualGuests = guestService.getPresentGuests(pageNumber, pageSize);

        // assert
        assertNotNull(actualGuests);
        assertEquals(expectedGuests.size(), actualGuests.size());
        assertEquals(expectedGuests, actualGuests);
        verify(guestRepo, times(1)).finCurrentGuests(pageable);
    }

    @Test
    void getPresentGuests_shouldEmptyList_whenNotFound() {
        // setup
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Slice<Guest> guestSlice = new SliceImpl<>(new ArrayList<>(), pageable, false);
        when(guestRepo.finCurrentGuests(pageable)).thenReturn(guestSlice);

        // execute
        List<Guest> actualGuests = guestService.getPresentGuests(pageNumber, pageSize);

        // assert
        assertTrue(actualGuests.isEmpty());
        verify(guestRepo, times(1)).finCurrentGuests(pageable);
    }

    @Test
    void getPresentGuests_shouldThrowBadInputPageNumberOrPageSizeException_whenInvalidPageNumber() {
        // setup
        int pageNumber = -1;
        int pageSize = 10;

        // execute & assert
        try {
            guestService.getPresentGuests(pageNumber, pageSize);
            fail("Expected BadInputPageNumberOrPageSizeException to be thrown");
        } catch (BadInputPageNumberOrPageSizeException e) {
            assertEquals("Invalid page number or page size", e.getMessage());
        }
    }

    @Test
    void getPresentGuests_shouldThrowBadInputPageNumberOrPageSizeException_whenInvalidPageSize() {
        // setup
        int pageNumber = 0;
        int pageSize = -10;

        // execute & assert
        try {
            guestService.getPresentGuests(pageNumber, pageSize);
            fail("Expected BadInputPageNumberOrPageSizeException to be thrown");
        } catch (BadInputPageNumberOrPageSizeException e) {
            assertEquals("Invalid page number or page size", e.getMessage());
        }
    }

    @Test
    void getPresentGuests_shouldThrowBadInputPageNumberOrPageSizeException_whenMaxPageSizeReached() {
        // setup
        int pageNumber = 0;
        int pageSize = 1000;

        // execute & assert
        try {
            guestService.getPresentGuests(pageNumber, pageSize);
            fail("Expected BadInputPageNumberOrPageSizeException to be thrown");
        } catch (BadInputPageNumberOrPageSizeException e) {
            assertEquals("Invalid page number or page size", e.getMessage());
        }
    }

    @Test
    void getPendingParcelsByCardId_shouldReturnResponse_whenGuestHasParcels() {
        // setup
        String cardId = "CARD123";
        Guest guest = new Guest();
        guest.setId(1L);
        guest.setName("John Doe");

        Parcel parcel1 = new Parcel(100L, "Laptop", false, guest);
        Parcel parcel2 = new Parcel(101L, "Bag", false, guest);

        when(guestRepo.findByCardId(cardId)).thenReturn(Optional.of(guest));
        when(parcelService.findPendingParcel(1L)).thenReturn(List.of(parcel1, parcel2));

        // execute
        PendingParcelsResponse response = guestService.getPendingParcelsByCardId(cardId);

        // assert
        assertEquals("John Doe", response.getGuestName());
        assertEquals(2, response.getPendingParcels().size());

        verify(guestRepo).findByCardId(cardId);
        verify(parcelService).findPendingParcel(1L);
    }

    @Test
    void getPendingParcelsByCardId_shouldThrowException_whenGuestNotFound() {
        // setup
        String cardId = "INVALID_CARD";

        when(guestRepo.findByCardId(cardId)).thenReturn(Optional.empty());

        // execute & assert
        try {
            guestService.getPendingParcelsByCardId(cardId);
            fail("Expected GuestNotFoundException to be thrown");
        } catch (GuestNotFoundException e) {
            assertEquals("Guest not found", e.getMessage());
        }

        verify(guestRepo).findByCardId(cardId);
        verifyNoInteractions(parcelService);
    }
}
