package com.moram.ssafe.dto.company;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CompanySaveRequest {

    @NotBlank(message = "빈문자열을 허용하지 않습니다.")
    private String companyName;

    @NotBlank(message = "빈문자열을 허용하지 않습니다.")
    private String engCompanyName;

    @NotNull(message = "null 을 허용하지 않습니다.")
    MultipartFile logoImg;
}
