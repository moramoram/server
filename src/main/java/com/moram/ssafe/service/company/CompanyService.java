package com.moram.ssafe.service.company;

import com.moram.ssafe.config.s3.S3Uploader;
import com.moram.ssafe.domain.company.Company;
import com.moram.ssafe.domain.company.CompanyQueryRepository;
import com.moram.ssafe.domain.company.CompanyRepository;
import com.moram.ssafe.domain.recruit.RecruitRepository;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.dto.company.CompanyResponse;
import com.moram.ssafe.exception.company.CompanyNotFoundException;
import com.moram.ssafe.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyQueryRepository companyQueryRepository;
    private final UserRepository userRepository;
    private final RecruitRepository recruitRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public Long createCompany(String companyName, String logoImg) {
        Company company = companyRepository.save(
                Company.builder()
                        .companyName(companyName)
                        .logoImg(logoImg)
                        .build());
        return company.getId();
    }

    public List<CompanyResponse> findCompanyList() {
        return companyRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .map(CompanyResponse::from).collect(Collectors.toList());
    }

    public CompanyResponse findCompany(Long companyId) {
        return CompanyResponse.from(getCompany(companyId));
    }

    public List<CompanyResponse> findCompanyName(String name) {
        List<Company> companyList = companyRepository.findByCompanyNameContaining(name);
        return companyList.stream()
                .map(CompanyResponse::from).collect(Collectors.toList());

    }

    public List<CompanyResponse> findUserCommentsByCompany(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        List<Company> companyList = companyQueryRepository.findUserCommentsByCompany(user.getId());
        return companyList.stream()
                .map(CompanyResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public void deleteCompany(Long companyId) {
        Company company = getCompany(companyId);
        s3Uploader.deleteObject(company.getLogoImg());
        recruitRepository.deleteByCompanyId(companyId);
        companyRepository.deleteById(company.getId());
    }

    @Transactional
    public Long updateCompanyName(Long id, String name) {
        Company originCompany = getCompany(id);
        originCompany.updateCompanyName(name);
        return originCompany.getId();
    }

    @Transactional
    public Long updateLogoImg(Long id, String logoImgUrl) {
        Company originCompany = getCompany(id);
        originCompany.updateLogoImg(logoImgUrl);
        return originCompany.getId();
    }

    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }
}
