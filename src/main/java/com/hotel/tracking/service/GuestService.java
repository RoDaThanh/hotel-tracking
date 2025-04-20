package com.hotel.tracking.service;

import com.hotel.tracking.exception.BadInputPageNumberOrPageSizeException;
import com.hotel.tracking.model.Guest;
import com.hotel.tracking.repository.GuestRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {
    public static final int MAX_PAGE_SIZE = 100;
    private final GuestRepo guestRepo;

    public GuestService(GuestRepo guestRepo) {
        this.guestRepo = guestRepo;
    }

    public List<Guest> getPresentGuests(int pageNumber, int pageSize) {
        if (pageNumber < 0 || pageSize <= 0 || pageSize > MAX_PAGE_SIZE) {
            throw new BadInputPageNumberOrPageSizeException("Invalid page number or page size");
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return guestRepo.finCurrentGuests(pageable).getContent();
    }

}
