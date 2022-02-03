package com.moram.ssafe.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserProfileImgRequest {

    @NotBlank(message = "빈문자열을 허용하지 않습니다.")
    @Size(max = 100, message = "255자 이하의 길이만 가능합니다.")
    private String profileImg;
}