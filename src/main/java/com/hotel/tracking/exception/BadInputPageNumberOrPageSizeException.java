package com.hotel.tracking.exception;

public class BadInputPageNumberOrPageSizeException extends RuntimeException {
  public BadInputPageNumberOrPageSizeException(String message) {
    super(message);
  }
}
