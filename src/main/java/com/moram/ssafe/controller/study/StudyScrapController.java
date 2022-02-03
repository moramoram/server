package com.moram.ssafe.controller.study;


import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.dto.common.response.CommonResponseDto;
import com.moram.ssafe.service.study.StudyScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.moram.ssafe.dto.common.response.SuccessMessage.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/study-scraps")
public class StudyScrapController {
    private final StudyScrapService studyScrapService;

    @PostMapping("/studies/{studyId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> pushScrap(@PathVariable Long studyId){
        return ResponseEntity.ok().body(CommonResponseDto.of(HttpStatus.OK,
                SUCCESS_PUSH_SCRAP, studyScrapService.pushScrap(studyId)));
    }

    @GetMapping("/users")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findUserScrap(){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_RECRUIT_SCRAP_LIST, studyScrapService.findUserScrap(UserContext.getCurrentUserId())));
    }

    @DeleteMapping("/{scrapId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> deleteScrap(@PathVariable Long scrapId){
        return ResponseEntity.ok().body(
                CommonResponseDto.of(HttpStatus.OK, SUCCESS_DELETE_SCRAP, studyScrapService.deleteScrap(scrapId)));
    }
}
