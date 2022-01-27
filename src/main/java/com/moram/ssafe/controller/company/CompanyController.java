package com.moram.ssafe.controller.company;

import com.moram.ssafe.config.s3.S3Uploader;
import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.dto.common.response.CommonResponseDto;
import com.moram.ssafe.dto.common.response.SuccessMessage;
import com.moram.ssafe.dto.company.CompanySaveRequest;
import com.moram.ssafe.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RequestMapping("/companies")
@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final S3Uploader s3Uploader;

    @PostMapping
    @PreAuthorize(roles = {"ROLE_ADMIN"})
    public ResponseEntity<CommonResponseDto> createCompany(@ModelAttribute @Valid CompanySaveRequest companySaveRequest) throws IOException {
        String logoImg = s3Uploader.upload(companySaveRequest.getLogoImg(), "static/company");
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_POST_COMPANY,
                companyService.createCompany(companySaveRequest.getCompanyName(), logoImg)));
    }

    @GetMapping
    public ResponseEntity<CommonResponseDto> findCompanyList() {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_COMPANY_LIST,
                companyService.findCompanyList()));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CommonResponseDto> findCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_COMPANY,
                companyService.findCompany(companyId)));
    }

    @GetMapping("/name")
    public ResponseEntity<CommonResponseDto> findCompanyName(@RequestParam String name) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_COMPANY_NAME,
                companyService.findCompanyName(name)));
    }

    @PutMapping("/{companyId}/name")
    @PreAuthorize(roles = {"ROLE_ADMIN"})
    public ResponseEntity<CommonResponseDto> updateCompanyName(@PathVariable Long companyId,@RequestParam String name) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_PUT_COMPANY_NAME,
                companyService.updateCompanyName(companyId,name)));
    }

    @PutMapping("/{companyId}/logo-img")
    @PreAuthorize(roles = {"ROLE_ADMIN"})
    public ResponseEntity<CommonResponseDto> updateLogoImg(@PathVariable Long companyId,@ModelAttribute MultipartFile logoImg) throws IOException{
        String logoImgUrl = s3Uploader.upload(logoImg, "static/company");
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_PUT_COMPANY_LOGO,
                companyService.updateLogoImg(companyId,logoImgUrl)));
    }

    @DeleteMapping("/{companyId}")
    @PreAuthorize(roles = {"ROLE_ADMIN"})
    public ResponseEntity<CommonResponseDto> deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.NO_CONTENT, SuccessMessage.SUCCESS_DELETE_COMPANY));
    }

}

