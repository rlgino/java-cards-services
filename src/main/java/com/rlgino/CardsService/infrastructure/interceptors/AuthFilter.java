package com.rlgino.CardsService.infrastructure.interceptors;

import com.rlgino.CardsService.infrastructure.security.JwtUtil;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@Order(2)
public class AuthFilter extends OncePerRequestFilter {

    @Value("${springdoc.api-docs.path}")
    private String apiDocsURL;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().contains("login") || request.getRequestURI().contains("swagger") || request.getRequestURI().contains(apiDocsURL);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChainfilterChain) throws ServletException, IOException {
        if (request.getRequestURI().contains("login") || request.getRequestURI().contains("swagger") || request.getRequestURI().contains(apiDocsURL)) filterChainfilterChain.doFilter(request, response);

        String authToken = request.getHeader("auth");
        if (authToken == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid auth token");
            return;
        }
        try {
            String username = JwtUtil.extractUsername(authToken);
            if (username == null || username.isEmpty()) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid auth token");
                return;
            }
            log.info("Username :: {}", username);
        } catch (MalformedJwtException e) {
            log.error(e.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid auth token");
            return;
        }
        filterChainfilterChain.doFilter(request, response);
    }
}
