package com.parking.ticket.payload.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;


public class SignInRequest {

    @NotBlank
    @ApiModelProperty(notes = "Username of the User", name = "user", required = true)
    private String username;

    @NotBlank
    @ApiModelProperty(notes = "Password of the User", name = "password", required = true)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
