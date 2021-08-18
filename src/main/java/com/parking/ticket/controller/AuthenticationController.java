package com.parking.ticket.controller;


import com.parking.ticket.payload.request.SignInRequest;
import com.parking.ticket.payload.request.SignUpRequest;
import com.parking.ticket.payload.response.JwtResponse;
import com.parking.ticket.payload.response.MessageResponse;
import com.parking.ticket.service.user.UserAuthenticationManager;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@ApiOperation(value = "/api/v1/auth", tags = "Authentication Controller")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final UserAuthenticationManager userAuthenticationManager;

    public AuthenticationController(UserAuthenticationManager userAuthenticationManager) {
        this.userAuthenticationManager = userAuthenticationManager;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signInUser(@Valid @RequestBody SignInRequest signInRequest) {

        JwtResponse jwtResponse = userAuthenticationManager.authenticateUserSignIn(signInRequest);

        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            MessageResponse jwtResponse = userAuthenticationManager.authenticateUserSignUp(signUpRequest);
            return new ResponseEntity<>(jwtResponse.getMessage(), jwtResponse.getHttpStatus());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
