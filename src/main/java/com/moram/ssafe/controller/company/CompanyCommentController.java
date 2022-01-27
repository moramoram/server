package com.moram.ssafe.controller.company;

import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.dto.common.response.CommonResponseDto;
import com.moram.ssafe.dto.common.response.SuccessMessage;
import com.moram.ssafe.dto.company.CompanyCommentRequest;
import com.moram.ssafe.dto.company.CompanyCommentUpdateRequest;
import com.moram.ssafe.service.company.CompanyCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/company-comments")
@RestController
@RequiredArgsConstructor
public class CompanyCommentController {

    private final CompanyCommentService companyCommentService;

    @PostMapping
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> createComment(@RequestBody @Valid CompanyCommentRequest request) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_POST_COMMENT,
                companyCommentService.createComment(request)));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CommonResponseDto> findCommentList(@PathVariable Long companyId) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_COMMENT_LIST,
                companyCommentService.findCommentList(companyId)));
    }

    @PutMapping
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> updateComment(@RequestBody @Valid CompanyCommentUpdateRequest request) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_PUT_COMMENT,
                companyCommentService.updateComment(request)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> deleteComment(@PathVariable Long id) {
        companyCommentService.deleteComment(id);
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.NO_CONTENT, SuccessMessage.SUCCESS_DELETE_COMMENT));
    }
}
