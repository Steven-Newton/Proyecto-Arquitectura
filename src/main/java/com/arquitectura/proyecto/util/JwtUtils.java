package com.arquitectura.proyecto.util;

import com.arquitectura.proyecto.constants.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;

import java.security.SignatureException;

public class JwtUtils {

    private static final String SECRET_KEY = "este es el secret de nuestro token"; // Use your actual secret key

    public static String getUsernameFromToken(String token) {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes()) // Ensure the secret key is in bytes
                    .build()
                    .parseClaimsJws(token.substring(Constants.BEARER_PREFIX.length()))
                    .getBody();
            return claims.get("sub").toString(); // The username is typically set as the subject in the token

    }
}