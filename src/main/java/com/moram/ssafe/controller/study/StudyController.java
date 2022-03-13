package com.moram.ssafe.controller.study;

import com.moram.ssafe.config.s3.S3Uploader;
import com.moram.ssafe.controller.user.annotation.AuthenticationPrincipal;
import com.moram.ssafe.controller.user.annotation.CurrentUser;
import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.dto.common.response.CommonResponseDto;
import com.moram.ssafe.dto.study.StudyFormRequest;
import com.moram.ssafe.dto.study.StudyRequestDto;
import com.moram.ssafe.dto.study.StudySearch;
import com.moram.ssafe.service.study.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

import static com.moram.ssafe.dto.common.response.SuccessMessage.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/studies")
public class StudyController {

    private final StudyService studyService;
    private final S3Uploader s3Uploader;

    @GetMapping
    public ResponseEntity<CommonResponseDto> findStudyList(@RequestParam int offset) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY_LIST, studyService.findStudyList(offset)));
    }

    @GetMapping("/{studyId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findStudy(@AuthenticationPrincipal CurrentUser currentUser,
                                                       @PathVariable Long studyId) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY, studyService.findStudy(currentUser.getId(), studyId)));
    }

    @GetMapping("/company-names")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findCompanyNameStudy(@RequestParam String companyName) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY_LIST, studyService.findCompanyNameStudy(companyName)));
    }

    @GetMapping("/users")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findUserStudy(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam int offset) {
        return ResponseEntity.ok().body(CommonResponseDto.of(HttpStatus.OK,
                SUCCESS_GET_BOARD_LIST_USER, studyService.findUserStudy(currentUser.getId(), offset)));
    }

    @GetMapping("/search")
    public ResponseEntity<CommonResponseDto> findByStudyNameAndType(@RequestParam(required = false) String title,
                                                                    @RequestParam(required = false) String studyType,
                                                                    @RequestParam(required = false) String criteria,
                                                                    @RequestParam int offset
    ) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY_NAME, studyService.findByStudyNameAndType(offset, StudySearch.of(title, studyType, criteria))));
    }

    @GetMapping("/views")
    public ResponseEntity<CommonResponseDto> findByLotsOfView(@RequestParam int offset) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY_VIEWS, studyService.findByLotsOfView(offset)));
    }

    @GetMapping("/scraps")
    public ResponseEntity<CommonResponseDto> findByLotsOfScrap(@RequestParam int offset) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY_SCRAP, studyService.findByLotsOfScrap(offset)));
    }

    @GetMapping("/comments/users")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findByUserComments(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY_LIST_COMMENTS, studyService.findByUserComments(currentUser.getId())));
    }

    @GetMapping("/scraps/users")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findUserScrap(@AuthenticationPrincipal CurrentUser currentUser,
                                                           @RequestParam int offset) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_RECRUIT_SCRAP_LIST,
                studyService.findUserScrap(currentUser.getId(), offset)));
    }

    @PutMapping("/{studyId}/scraps")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> toggleStudyScraps(@AuthenticationPrincipal CurrentUser currentUser,
                                                               @PathVariable Long studyId) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.CREATED, SUCCESS_PUT_STUDY_SCRAP,
                studyService.toggleStudyScraps(currentUser.getId(), studyId)));
    }

    @PostMapping
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> createStudy(@AuthenticationPrincipal CurrentUser currentUser,
                                                         @Valid StudyFormRequest request) throws IOException {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.CREATED, SUCCESS_POST_STUDY, studyService.createStudy(currentUser.getId(), convertStudyRequestDto(request))));
    }

    @PutMapping("/{studyId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> updateStudy(@AuthenticationPrincipal CurrentUser currentUser,
                                                         @PathVariable Long studyId, @Valid StudyFormRequest request)
            throws IOException {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.CREATED, SUCCESS_UPDATE_STUDY, studyService.updateStudy(currentUser.getId(),
                        studyId, convertStudyRequestDto(request))));
    }

    public StudyRequestDto convertStudyRequestDto(StudyFormRequest request) throws IOException {
        String thumbnailImg = "";
        if (!request.getThumbnailImg().isEmpty()
                || request.getThumbnailImg() == null) {
            thumbnailImg = s3Uploader.upload(request.getThumbnailImg(), "etc");
        }
        StudyRequestDto studyRequestDto = StudyRequestDto.builder()
                .companyName(request.getCompanyName())
                .title(request.getTitle())
                .studyType(request.getStudyType())
                .techStack(request.getTechStack())
                .memberNumber(request.getMemberNumber())
                .thumbnailImg(thumbnailImg)
                .onOff(request.getOnOff())
                .content(request.getContent())
                .build();
        return studyRequestDto;
    }

    @PutMapping("{studyId}/recruitments")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> updateRecruitment(@AuthenticationPrincipal CurrentUser currentUser,
                                                               @PathVariable Long studyId) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.CREATED, SUCCESS_UPDATE_STUDY, studyService.updateRecruitment(currentUser.getId(), studyId)));
    }

    @DeleteMapping("/{studyId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> deleteStudy(@AuthenticationPrincipal CurrentUser currentUser,
                                                         @PathVariable Long studyId) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.NO_CONTENT, SUCCESS_DELETE_BOARD, studyService.deleteStudy(currentUser.getId(), studyId)));
    }
}
