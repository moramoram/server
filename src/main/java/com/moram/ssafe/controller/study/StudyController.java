package com.moram.ssafe.controller.study;

import com.moram.ssafe.config.s3.S3Uploader;
import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.controller.user.annotation.UserContext;
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
    public ResponseEntity<CommonResponseDto> findStudy(@PathVariable Long studyId) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY, studyService.findStudy(studyId)));
    }

    @GetMapping("/users")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findUserStudy(@RequestParam int offset) {
        return ResponseEntity.ok().body(CommonResponseDto.of(HttpStatus.OK,
                SUCCESS_GET_BOARD_LIST_USER, studyService.findUserStudy(UserContext.getCurrentUserId(), offset)));
    }

    @GetMapping("/search")
    public ResponseEntity<CommonResponseDto> findByStudyNameAndType(@RequestParam int offset,
                                                                    @RequestBody StudySearch studySearch) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY_NAME, studyService.findByStudyNameAndType(offset, studySearch)));
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
    public ResponseEntity<CommonResponseDto> findByUserComments() {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY_LIST_COMMENTS, studyService.findByUserComments()));
    }

    @PostMapping
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> createStudy(@Valid StudyFormRequest request) throws IOException {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_POST_STUDY, studyService.createStudy(convertStudyRequestDto(request))));
    }


    @PutMapping("/{studyId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> updateStudy(@PathVariable Long studyId, @Valid StudyFormRequest request) throws IOException {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_UPDATE_STUDY, studyService.updateStudy(studyId, convertStudyRequestDto(request))));
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
    public ResponseEntity<CommonResponseDto> updateRecruitment(@PathVariable Long studyId) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_UPDATE_STUDY, studyService.updateRecruitment(studyId)));
    }

    @DeleteMapping("/{studyId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> deleteStudy(@PathVariable Long studyId) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_DELETE_BOARD, studyService.deleteStudy(studyId)));
    }
}
