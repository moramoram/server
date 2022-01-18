package com.moram.ssafe.controller.user.auth;

import com.moram.ssafe.dto.user.auth.LoginRequest;
import com.moram.ssafe.service.user.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login/{socialType}")
    public ResponseEntity<Void> login(@PathVariable String socialType, @RequestBody LoginRequest loginRequest,
                                      HttpServletResponse response) {
//        TokenResponse tokenResponse = authService.createAccessToken(socialType, loginRequest);
//        Long id = authService.extractIdByToken(tokenResponse.getAccessToken(), JwtTokenType.ACCESS_TOKEN);
//        ResponseCookie responseCookie = createRefreshTokenCookie(id);

    log.info(loginRequest.getCode());
        return new ResponseEntity<>(HttpStatus.CREATED);
//        return ResponseEntity.ok(tokenResponse);
    }
}
