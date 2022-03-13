package com.moram.ssafe.controller.user.auth;

import com.moram.ssafe.dto.common.response.CommonResponseDto;
import com.moram.ssafe.service.user.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.moram.ssafe.dto.common.response.SuccessMessage.SUCCESS_POST_LOGIN;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login/{socialType}")
    public ResponseEntity<CommonResponseDto> login(@PathVariable String socialType, @RequestParam("code") String code) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.CREATED, SUCCESS_POST_LOGIN, authService.login(socialType, code)));
    }
}
