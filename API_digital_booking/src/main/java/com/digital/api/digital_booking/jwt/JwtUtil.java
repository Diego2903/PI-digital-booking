package com.digital.api.digital_booking.jwt;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JwtUtil {
    String extractUserName(String token,String secretKey);
    String generateToken(UserDetails userDetails,
                         long systemCurrentMillis,
                         long configuredExpirationTimeInMillis,
                         String secretKey);

    Date extractExpiration(String token,String secretKey);

    String extractEmail(String token,String secretKey);

}
