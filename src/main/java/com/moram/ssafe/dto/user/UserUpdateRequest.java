package com.moram.ssafe.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {

    @NotNull(message = "프로필 이미지가 존재하지 않습니다.")
    private String profileImg;

    @NotNull(message = "닉네임이 존재하지 않습니다.")
    private String nickname;

    @NotNull(message = "실명이 존재하지 않습니다.")
    private String realName;

    @NotNull(message = "Campus가 존재하지 않습니다.")
    private String campus;

    @NotNull(message = "싸피 인증이미지가 존재하지 않습니다.")
    private String authImg;

}
