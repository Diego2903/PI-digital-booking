package com.digital.api.digital_booking.services;


import com.digital.api.digital_booking.dto.AuthenticationResponse;
import com.digital.api.digital_booking.dto.LoginRequest;
import com.digital.api.digital_booking.dto.SignUpRequest;
import com.digital.api.digital_booking.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;


    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));

        String jwt =jwtService
                .generateToken((UserDetails) authentication.getPrincipal());

        return AuthenticationResponse.builder()
                .jwt(jwt)
                .build();
    }

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        UserDetails userDetails = userService.createUser(signUpRequest);


        return AuthenticationResponse.builder()
                .jwt(jwtService.generateToken(userDetails))
                .build();
    }
}
