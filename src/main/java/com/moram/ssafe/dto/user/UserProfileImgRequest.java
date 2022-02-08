package com.moram.ssafe.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserProfileImgRequest {

    @NotNull(message = "null을 허용하지 않습니다.")
    private MultipartFile profileImg;
}