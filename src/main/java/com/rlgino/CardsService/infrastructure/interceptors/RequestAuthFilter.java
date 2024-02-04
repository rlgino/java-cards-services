package com.rlgino.CardsService.infrastructure.interceptors;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
@Order(1)
public class RequestAuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("BasicAuthInterceptor::preHandle()");

        HttpServletRequest req = (HttpServletRequest) request;
        if(req.getRequestURI().contains("swagger-ui")) return;

        String authHeader = req.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Barear ")) {
            if (authHeader.contains("admin")){
                chain.doFilter(request, response);
                return;
            }
        }

        HttpServletResponse resp = (HttpServletResponse) response;
        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
