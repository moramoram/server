package com.moram.ssafe.dto.company;

import com.moram.ssafe.domain.company.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyResponse {

    private Long companyId;

    private String companyName;

    private String engCompanyName;

    private String logoImg;

    public static CompanyResponse from(Company company) {
        return new CompanyResponse(company.getId(), company.getCompanyName(),
                company.getEngCompanyName(), company.getLogoImg());
    }
}
