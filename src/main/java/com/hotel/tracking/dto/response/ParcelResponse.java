package com.hotel.tracking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ParcelResponse implements Serializable {
    private Long id;
    private String description;
    private boolean isPickedUp;
}