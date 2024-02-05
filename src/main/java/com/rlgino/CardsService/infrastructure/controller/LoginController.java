package com.rlgino.CardsService.infrastructure.controller;

import com.rlgino.CardsService.infrastructure.security.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @PostMapping
    public String login(@RequestBody LoginRequest request) {
        return JwtUtil.generateToken(request.getUsername());
    }

}

final class LoginRequest {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
