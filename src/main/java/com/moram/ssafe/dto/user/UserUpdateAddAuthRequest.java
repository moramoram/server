package com.moram.ssafe.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserUpdateAddAuthRequest {

    @NotNull(message = "프로필 이미지가 존재하지 않습니다.")
    @Size(min = 5, max = 50 ,message="길이가 5이상 50 이하여야 가능합니다")
    private String profileImg;

    @NotBlank(message = "닉네임이 존재하지 않습니다.")
    @Length(min = 2, max = 8 ,message="길이가 2이상 8 이하여야 가능합니다")
    private String nickname;

    @NotBlank(message = "실명이 존재하지 않습니다.")
    @Length(max = 5 ,message="길이가 1이상 5 이하여야 가능합니다")
    private String realName;

    @NotNull(message = "ordinal이 존재하지 않습니다.")
    private Integer ordinal;

    @NotBlank(message = "Campus가 존재하지 않습니다.")
    @Size(min = 1, max = 5 ,message="길이가 1이상 5 이하여야 가능합니다")
    private String campus;

    @NotBlank(message = "싸피 인증이미지가 존재하지 않습니다.")
    @Size(min = 3, max = 255 ,message="길이가 3이상 255 이하여야 가능합니다")
    private String authImg;

}
