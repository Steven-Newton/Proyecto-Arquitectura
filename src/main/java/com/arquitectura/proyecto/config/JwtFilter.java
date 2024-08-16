/**
 * 
 */
package com.arquitectura.proyecto.config;

import com.arquitectura.proyecto.constants.Constants;
import com.arquitectura.proyecto.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

 @Slf4j
 @Component
public class JwtFilter extends OncePerRequestFilter {
	 public static final String secret = "este es el secret de nuestro token";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader(Constants.AUTHORIZATION_HEADER);
		if (isPrivate(request)){
			if (isSecuredMethod(request)) {
				if (authHeader == null || !authHeader.startsWith(Constants.BEARER_PREFIX)) {
					response.getWriter().write(new ObjectMapper().writeValueAsString(new ErrorResponse("invalid Token Form")));
					response.setStatus(HttpStatus.OK.value());
					response.setContentType("application/json");
					return;
				}
				String token = authHeader.substring(Constants.BEARER_PREFIX.length());
				try {
					Claims claims = Jwts.parser()
							.setSigningKey(secret.getBytes()) // Ensure the secret key is in bytes
							.build()
							.parseClaimsJws(token)
							.getBody();
					claims.forEach((key, value) -> {
						System.out.println("key: " + key + " value: " + value);
					});
				} catch (SignatureException | MalformedJwtException | ExpiredJwtException e) {
					response.getWriter().write(new ObjectMapper().writeValueAsString(new ErrorResponse("invalid Token")));
					response.setStatus(HttpStatus.OK.value());
					response.setContentType("application/json");
					return;
				}
			}
		}
		filterChain.doFilter(request, response);
	}

	private boolean isSecuredMethod(HttpServletRequest request) {
		String method = request.getMethod();
		return "POST".equals(method) || "PUT".equals(method) || "DELETE".equals(method) || "GET".equals(method);
	}

	private boolean isPrivate(HttpServletRequest request) {
		return request.getRequestURI().contains("/api/private");
	}

}
