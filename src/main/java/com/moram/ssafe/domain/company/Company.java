package com.moram.ssafe.domain.company;

import com.moram.ssafe.domain.BaseEntity;
import com.moram.ssafe.dto.company.CompanyNameRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_company")
public class Company extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id", updatable = false)
    private Long id;

    private String companyName;

    private String engCompanyName;

    private String logoImg;

    @Builder
    public Company(String companyName, String engCompanyName, String logoImg) {
        this.companyName = companyName;
        this.engCompanyName = engCompanyName;
        this.logoImg = logoImg;
    }

    public void updateCompanyName(CompanyNameRequest request) {
        this.companyName = request.getCompanyName();
        this.engCompanyName = request.getEngCompanyName();
    }

    public void updateLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

}
