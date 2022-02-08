package com.moram.ssafe.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {

    private Long userId;
    private String email;
    private String nickname;
    private int authCheck;
    private String accessToken;
    private String refreshToken;

    @Builder
    public LoginResponse(Long userId, String email, String nickname, int authCheck, String refreshToken, String accessToken) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.authCheck = authCheck;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }
}
