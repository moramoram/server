package com.moram.ssafe.controller.study;

import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.dto.common.response.CommonResponseDto;
import com.moram.ssafe.dto.study.StudySaveRequest;
import com.moram.ssafe.dto.study.StudyUpdateRequest;
import com.moram.ssafe.service.study.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.moram.ssafe.dto.common.response.SuccessMessage.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/studies")
public class StudyController {
    private final StudyService studyService;

    @PostMapping
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> createStudy(@RequestBody @Valid StudySaveRequest request){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_POST_STUDY, studyService.createStudy(request)));
    }

    @GetMapping
    public ResponseEntity<CommonResponseDto> findStudyList(@RequestParam(value = "limit", defaultValue = "1")
                                                                 int limit){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY_LIST, studyService.findStudyList(limit)));
    }

    @GetMapping("/{studyId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findStudy(@PathVariable Long studyId){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY, studyService.findStudy(studyId)));
    }

    @GetMapping("/users")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findUserStudy(){
        return ResponseEntity.ok().body(CommonResponseDto.of(HttpStatus.OK,
                SUCCESS_GET_BOARD_LIST_USER, studyService.findUserStudy(UserContext.getCurrentUserId())));
    }

    @GetMapping("/name")
    public ResponseEntity<CommonResponseDto> findByStudyName(@RequestParam String name,
                                                             @RequestParam(value = "limit", defaultValue = "1") int limit){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY_NAME, studyService.findByStudyName(name, limit)));
    }

    @GetMapping("/views")
    public ResponseEntity<CommonResponseDto> findByLotsOfView( @RequestParam(value = "limit", defaultValue = "1") int limit){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY_VIEWS, studyService.findByLotsOfView(limit)));
    }

    @GetMapping("/scraps")
    public ResponseEntity<CommonResponseDto> findByLotsOfScrap( @RequestParam(value = "limit", defaultValue = "1") int limit){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY_SCRAP, studyService.findByLotsOfScrap(limit)));
    }

    @PutMapping("/{studyId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> updateStudy(@PathVariable Long studyId, @RequestBody @Valid StudyUpdateRequest request){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_UPDATE_STUDY, studyService.updateStudy(studyId, request)));
    }

    @DeleteMapping("/{studyId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> deleteStudy(@PathVariable Long studyId){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_DELETE_BOARD, studyService.deleteStudy(studyId)));
    }
}
