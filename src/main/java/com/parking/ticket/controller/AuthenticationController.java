package com.parking.ticket.controller;


import com.parking.ticket.payload.request.LoginRequest;
import com.parking.ticket.payload.request.SignupRequest;
import com.parking.ticket.payload.response.JwtResponse;
import com.parking.ticket.payload.response.MessageResponse;
import com.parking.ticket.service.user.UserAuthenticationManager;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@ApiOperation(value = "/api/v1/auth", tags = "Authentication Controller")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private UserAuthenticationManager userAuthenticationManager;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signInUser(@Valid @RequestBody LoginRequest loginRequest) {

        JwtResponse jwtResponse = userAuthenticationManager.authenticateUserSignIn(loginRequest);

        return ResponseEntity.ok(jwtResponse);
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody SignupRequest signupRequest) {
        try {
            MessageResponse jwtResponse = userAuthenticationManager.authenticateUserSignUp(signupRequest);
            return new ResponseEntity<>(jwtResponse.getMessage(), jwtResponse.getHttpStatus());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
