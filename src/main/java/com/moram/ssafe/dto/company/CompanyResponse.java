package com.moram.ssafe.dto.company;

import com.moram.ssafe.domain.company.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponse {

    private Long companyId;

    private String companyName;

    private String logoImg;

    public static CompanyResponse of(Company company){
        return CompanyResponse.builder()
                .companyId(company.getId())
                .companyName(company.getCompanyName())
                .logoImg(company.getLogoImg())
                .build();
    }
}
