package com.digital.api.digital_booking.controllers;


import com.digital.api.digital_booking.dto.AuthenticationResponse;
import com.digital.api.digital_booking.dto.LoginRequest;
import com.digital.api.digital_booking.dto.SignUpRequest;
import com.digital.api.digital_booking.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Authentication")
//@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Login with user and password and returns JWT token", responses = {
            @ApiResponse(responseCode = "200",description = "Successful Operation", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content)})
    @PostMapping("/api/login")
    public AuthenticationResponse login(@RequestBody @Valid @NonNull LoginRequest loginRequest) {
        System.out.println("loginRequest: " + loginRequest);
        return authenticationService.login(loginRequest);
    }

    @Operation(summary = "Sign up and returns JWT token", responses = {
            @ApiResponse(responseCode = "200",description = "Successful Operation", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)})
    @PostMapping("/api/sign-up")
    public AuthenticationResponse signUp(@RequestBody @Valid @NonNull SignUpRequest signUpRequest) {
        return authenticationService.signUp(signUpRequest);
    }




}
