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
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> save(@RequestBody @Valid StudySaveRequest request){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_POST_STUDY, studyService.save(request)));
    }

    @GetMapping
    public ResponseEntity<CommonResponseDto> findAll(@RequestParam(value = "limit", defaultValue = "1")
                                                                 int limit){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY_LIST, studyService.findAll(limit)));
    }

    @GetMapping("/{studyId}")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> findById(@PathVariable Long studyId){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_STUDY, studyService.findById(studyId)));
    }

    @GetMapping("/users")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> findByUserId(){
        return ResponseEntity.ok().body(CommonResponseDto.of(HttpStatus.OK,
                SUCCESS_GET_BOARD_LIST_USER, studyService.findByUserId(UserContext.getCurrentUserId())));
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
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> update(@PathVariable Long studyId, @RequestBody @Valid StudyUpdateRequest request){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_UPDATE_STUDY, studyService.update(studyId, request)));
    }

    @DeleteMapping("/{studyId}")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> delete(@PathVariable Long studyId){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_DELETE_BOARD, studyService.delete(studyId)));
    }
}
