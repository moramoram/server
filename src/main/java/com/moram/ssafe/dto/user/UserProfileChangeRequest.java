package com.moram.ssafe.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserProfileChangeRequest {

    @NotBlank(message = "빈문자열을 허용하지 않습니다.")
    @Size(min = 2, max = 8, message = "2~8자의 닉네임만 가능합니다.")
    private String nickname;

    @NotNull(message = "null을 허용하지 않습니다.")
    private MultipartFile profileImg;
}