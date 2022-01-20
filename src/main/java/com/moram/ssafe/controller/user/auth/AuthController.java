package com.moram.ssafe.controller.user.auth;

import com.moram.ssafe.dto.user.LoginResponse;
import com.moram.ssafe.service.user.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login/{socialType}")
    public ResponseEntity<LoginResponse> login(@PathVariable String socialType, @RequestParam("code") String code) {
        log.info(socialType+" "+code);
        LoginResponse loginResponse = authService.login(socialType, code);
        return ResponseEntity.ok().body(loginResponse);
    }
}
