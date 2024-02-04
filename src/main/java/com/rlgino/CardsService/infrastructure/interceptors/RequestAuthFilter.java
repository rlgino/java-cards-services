package com.rlgino.CardsService.infrastructure.interceptors;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
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

        HttpServletRequest req = (HttpServletRequest)request;
        log.info("{} --> {}", req.getMethod(), req.getRequestURI());

        long start = System.currentTimeMillis();
        chain.doFilter(req, response);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        log.info("Elapsed time: {} ms", timeElapsed);
    }
}
