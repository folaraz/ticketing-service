package com.parking.ticket.payload.response;

import org.springframework.http.HttpStatus;

public class MessageResponse {
    private String message;

    private HttpStatus httpStatus;

    public MessageResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
