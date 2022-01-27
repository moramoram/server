package com.moram.ssafe.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateAddAuth {

    private String profileImg;

    private String nickname;

    private String realName;

    private Integer ordinal;

    private String campus;

    private String authImg;

    @Builder
    public UserUpdateAddAuth(String profileImg, String nickname, String realName, Integer ordinal, String campus,
                              String authImg) {
        this.profileImg = profileImg;
        this.nickname = nickname;
        this.realName = realName;
        this.ordinal = ordinal;
        this.campus = campus;
        this.authImg = authImg;
    }

}
