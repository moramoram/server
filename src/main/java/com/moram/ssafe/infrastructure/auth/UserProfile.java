package com.moram.ssafe.infrastructure.auth;


import com.moram.ssafe.domain.user.Role;
import com.moram.ssafe.domain.user.SocialType;
import com.moram.ssafe.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserProfile {
    private final String oauthId;
    private final String email;
    private final String name;

    @Builder
    public UserProfile(String oauthId, String email, String name) {
        this.oauthId = oauthId;
        this.email = email;
        this.name = name;
    }

    public User toUser() {
        return User.builder()
                .email(email)
                .roleType(Role.USER)
                .nickname(name)
                .build();
    }
}
