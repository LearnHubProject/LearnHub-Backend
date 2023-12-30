package org.learnhub.backend.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.learnhub.backend.service.UserService;
import org.learnhub.backend.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Service
public class JWTTokenFilter extends OncePerRequestFilter {

    @Autowired
    UserService userService;

    @Autowired
    JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            String tokenHeader = request.getHeader("Authorization").replace("Bearer ", "");

            DecodedJWT tokenData = jwtUtils.validateToken(tokenHeader);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(tokenData.getClaim("username").asString(), null, Collections.singletonList(new SimpleGrantedAuthority(tokenData.getClaim("role").asString())));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        } catch (Exception e) {
            //TODO: Create a proper validation of JWT token
        }

        chain.doFilter(request, response);
    }

}