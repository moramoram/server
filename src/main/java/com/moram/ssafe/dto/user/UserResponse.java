package com.moram.ssafe.dto.user;

import com.moram.ssafe.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {

    private String nickname;

    private int ordinal;

    private String campus;

    private int authCheck;

    public static UserResponse from(User user) {
        return new UserResponse(user.getNickname(), user.getOrdinal(),
                user.getCampus(), user.getAuthCheck());
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
