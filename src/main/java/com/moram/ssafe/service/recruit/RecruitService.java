package com.moram.ssafe.service.recruit;

import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.domain.company.Company;
import com.moram.ssafe.domain.company.CompanyRepository;
import com.moram.ssafe.domain.recruit.Recruit;
import com.moram.ssafe.domain.recruit.RecruitQueryRepository;
import com.moram.ssafe.domain.recruit.RecruitRepository;
import com.moram.ssafe.domain.recruit.RecruitScrap;
import com.moram.ssafe.dto.recruit.RecruitResponse;
import com.moram.ssafe.dto.recruit.RecruitSaveRequest;
import com.moram.ssafe.dto.recruit.RecruitScrapResponse;
import com.moram.ssafe.dto.recruit.RecruitUpdateRequest;
import com.moram.ssafe.exception.company.CompanyNotFoundException;
import com.moram.ssafe.exception.recruit.RecruitNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitService {

    private final RecruitRepository recruitRepository;
    private final RecruitQueryRepository recruitQueryRepository;
    private final CompanyRepository companyRepository;


    public List<RecruitResponse> findRecruitList() {
        return recruitRepository.findAll().stream()
                .map(RecruitResponse::from).collect(Collectors.toList());
    }

    public RecruitResponse findRecruit(Long id) {
        Recruit recruit = getRecruit(id);
        return RecruitResponse.from(recruit);
    }

    public List<RecruitResponse> findUserRecruitScrapList() {
        Long userId = UserContext.getCurrentUserId();

        List<Recruit> recruits = recruitRepository.findByUserScrap(userId);
        return recruits.stream()
                .map(RecruitResponse::from).collect(Collectors.toList());
    }

    public List<RecruitResponse> findRecruitBenefit(int limit) {
        return recruitRepository.findByRecruitBenefit(
                PageRequest.of(limit - 1, 2, Sort.by("createdDate").descending()))
                .stream().map(RecruitResponse::from).collect(Collectors.toList());
    }

    public List<RecruitResponse> findRecruitLatest(int limit) {
        return recruitRepository.findByRecruitLatest(
                PageRequest.of(limit - 1, 2, Sort.by("createdDate").descending()))
                .stream().map(RecruitResponse::from).collect(Collectors.toList());
    }

    public List<RecruitResponse> findByLotsOfScrap(int limit) {
        return recruitQueryRepository.findByLotsOfScrap(PageRequest.of(limit - 1, 2))
                .stream().map(RecruitResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public RecruitScrapResponse toggleRecruitScraps(Long recruitId) {
        Long userId = UserContext.getCurrentUserId();
        Recruit recruit = getRecruit(recruitId);
        RecruitScrap recruitScrap = RecruitScrap.builder()
                .recruit(recruit)
                .userId(userId)
                .build();
        return RecruitScrapResponse.from(
                recruit.toggleRecruitScarp(recruitScrap)
        );
    }

    public Recruit getRecruit(Long id) {
        return recruitRepository.findById(id).orElseThrow(RecruitNotFoundException::new);
    }

    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    @Transactional
    public Long createRecruit(RecruitSaveRequest request) {
        Company company = getCompany(request.getCompanyId());
        Recruit recruit = recruitRepository.save(request.toRecruit(company));
        return recruit.getId();
    }

    @Transactional
    public Long updateRecruit(Long id, RecruitUpdateRequest request) {
        Company company = getCompany(request.getCompanyId());
        Recruit recruit = getRecruit(id);
        recruit.update(request.toRecruit(company));
        return recruit.getId();
    }

    @Transactional
    public void deleteRecruit(Long id) {
        Recruit recruit = getRecruit(id);
        recruitRepository.deleteById(recruit.getId());
    }

    @Transactional
    public void addView(Long id) {
        Recruit recruit = getRecruit(id);
        recruit.addView();
    }

}
