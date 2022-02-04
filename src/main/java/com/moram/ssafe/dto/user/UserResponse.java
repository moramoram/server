package com.moram.ssafe.dto.user;

import com.moram.ssafe.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {

    private String nickname;

    private Integer ordinal;

    private String campus;

    private int authCheck;

    public static UserResponse from(User user) {
        return new UserResponse(user.getNickname(), user.getOrdinal(),
                user.getCampus(), user.getAuthCheck());
    }

    public static UserResponse from_anon(User user){
        return new UserResponse("익명", null, null, user.getAuthCheck());
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
