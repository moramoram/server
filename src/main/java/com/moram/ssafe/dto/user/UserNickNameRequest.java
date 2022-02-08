package com.moram.ssafe.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserNickNameRequest {

    @NotBlank(message = "빈문자열을 허용하지 않습니다.")
    @Size(min = 2, max = 10, message = "2~10자의 닉네임만 가능합니다.")
    private String nickname;
}
