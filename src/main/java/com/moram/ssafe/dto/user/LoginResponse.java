package com.moram.ssafe.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {
    private String email;
    private String nickname;
    private String accessToken;

    @Builder
    public LoginResponse( String email, String nickname, String accessToken) {
        this.email = email;
        this.nickname = nickname;
        this.accessToken = accessToken;
    }
}
