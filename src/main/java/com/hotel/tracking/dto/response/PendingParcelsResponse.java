package com.hotel.tracking.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
public class PendingParcelsResponse implements Serializable {
    @Schema(description = "Full name of the guest", example = "John Doe")
    private String guestName;

    @Schema(description = "List of pending parcels for the guest")
    private List<ParcelResponse> pendingParcels;
}
