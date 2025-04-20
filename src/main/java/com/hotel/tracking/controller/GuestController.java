package com.hotel.tracking.controller;

import com.hotel.tracking.dto.response.PendingParcelsResponse;
import com.hotel.tracking.exception.ErrorResponse;
import com.hotel.tracking.model.Guest;
import com.hotel.tracking.service.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
@Tag(name = "Guest Controller", description = "Endpoints for guest-related operations")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/present")
    @Operation(
            summary = "Get currently checked-in guests",
            description = "Returns a paginated list of guests who are currently checked in.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved list of present guests",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Guest.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid page number or page size provided (e.g., negative page, size > 100)",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    public ResponseEntity<List<Guest>> getPresentGuests(
            @Parameter(description = "Page number for pagination", example = "0")
            @RequestParam(defaultValue = "0") int pageNumber,
            @Parameter(description = "Page size for pagination", example = "10")
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        List<Guest> guests = guestService.getPresentGuests(pageNumber, pageSize);
        return ResponseEntity.ok(guests);
    }

    @GetMapping("/{cardId}/parcels/pending")
    @Operation(
            summary = "Get Pending Parcels by Guest Card ID",
            description = "Returns a list of parcels that have not been picked up yet for the specified guest."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of pending parcels",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PendingParcelsResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Guest not found with the provided card ID",
                    // TODO add 404 sample response
                    content = @Content
            )
    })
    public ResponseEntity<PendingParcelsResponse> getPendingParcelsByCardId(
            @Parameter(description = "The card ID of the guest", example = "C2017", required = true)
            @PathVariable String cardId) {
        PendingParcelsResponse response = guestService.getPendingParcelsByCardId(cardId);
        return ResponseEntity.ok(response);
    }

}
