package com.moram.ssafe.dto.user.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefreshTokenResponse {

    private String refreshToken;

    @Builder
    public RefreshTokenResponse(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
