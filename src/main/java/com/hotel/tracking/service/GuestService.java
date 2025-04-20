package com.hotel.tracking.service;

import com.hotel.tracking.dto.response.ParcelResponse;
import com.hotel.tracking.dto.response.PendingParcelsResponse;
import com.hotel.tracking.exception.BadInputPageNumberOrPageSizeException;
import com.hotel.tracking.exception.GuestNotFoundException;
import com.hotel.tracking.model.Guest;
import com.hotel.tracking.model.Parcel;
import com.hotel.tracking.repository.GuestRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestService {
    public static final int MAX_PAGE_SIZE = 100;
    private final GuestRepo guestRepo;
    private final ParcelService parcelService;

    public GuestService(GuestRepo guestRepo, ParcelService parcelService) {
        this.guestRepo = guestRepo;
        this.parcelService = parcelService;
    }

    public List<Guest> getPresentGuests(int pageNumber, int pageSize) {
        if (pageNumber < 0 || pageSize <= 0 || pageSize > MAX_PAGE_SIZE) {
            throw new BadInputPageNumberOrPageSizeException("Invalid page number or page size");
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return guestRepo.finCurrentGuests(pageable).getContent();
    }

    public PendingParcelsResponse getPendingParcelsByCardId(String cardId) {
        Guest guest = guestRepo.findByCardId(cardId)
                .orElseThrow(() -> new GuestNotFoundException("Guest not found"));

        List<Parcel> pendingParcels = parcelService.findPendingParcel(guest.getId());
        List<ParcelResponse> parcelResponseList = new ArrayList<>();

        if (!pendingParcels.isEmpty()) {
            parcelResponseList = pendingParcels.stream().map(p ->
                            new ParcelResponse(p.getId(), p.getDescription(), p.isPickedUp()))
                    .collect(Collectors.toList());
        }

        return new PendingParcelsResponse(guest.getName(), parcelResponseList);
    }

}
