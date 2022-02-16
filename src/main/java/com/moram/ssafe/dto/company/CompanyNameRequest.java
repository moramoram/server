package com.moram.ssafe.dto.company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CompanyNameRequest {
    
    @NotBlank(message = "빈문자열을 허용하지 않습니다.")
    private String companyName;

    @NotBlank(message = "빈문자열을 허용하지 않습니다.")
    private String engCompanyName;
}
