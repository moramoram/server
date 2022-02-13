package com.moram.ssafe.controller.recruit;

import com.moram.ssafe.controller.user.annotation.AuthenticationPrincipal;
import com.moram.ssafe.controller.user.annotation.CurrentUser;
import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.dto.common.response.CommonResponseDto;
import com.moram.ssafe.dto.common.response.SuccessMessage;
import com.moram.ssafe.dto.recruit.RecruitSaveRequest;
import com.moram.ssafe.dto.recruit.RecruitSearch;
import com.moram.ssafe.dto.recruit.RecruitUpdateRequest;
import com.moram.ssafe.service.recruit.RecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/recruits")
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitService recruitService;

    @GetMapping
    public ResponseEntity<CommonResponseDto> findRecruitList() {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_RECRUIT_LIST, recruitService.findRecruitList()));
    }

    @GetMapping("/{id}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findUserRecruit(@AuthenticationPrincipal CurrentUser currentUser,
                                                             @PathVariable Long id) {
        recruitService.addView(id);
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_RECRUIT, recruitService.findUserRecruit(currentUser.getId(), id)));
    }

    @GetMapping("/benefits")
    public ResponseEntity<CommonResponseDto> findRecruitBenefit(@RequestParam int offset) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_RECRUIT_BENEFIT, recruitService.findRecruitBenefit(offset)));
    }

    @GetMapping("/popularity")
    public ResponseEntity<CommonResponseDto> findByLotsOfScrap(@RequestParam int offset) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_RECRUIT_POPULARITY, recruitService.findByLotsOfScrap(offset)));
    }

    @GetMapping("/latest")
    public ResponseEntity<CommonResponseDto> findRecruitLatest(@RequestParam int offset) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_RECRUIT_LATEST, recruitService.findRecruitLatest(offset)));
    }

    @GetMapping("/close-date")
    public ResponseEntity<CommonResponseDto> findRecruitCloseDate(@RequestParam int offset) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_RECRUIT_CLOSE_DATE, recruitService.findRecruitCloseDate(offset)));
    }

    @GetMapping("/search")
    public ResponseEntity<CommonResponseDto> findRecruitTitleAndTechStack(@RequestParam(required = false) String title,
                                                                          @RequestParam(required = false) List<String> techStack,
                                                                          @RequestParam(required = false) String job,
                                                                          @RequestParam(required = false) String criteria,
                                                                          @RequestParam(required = false) int offset) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_RECRUIT_LIST, recruitService.findRecruitTitleAndTechStack(offset,
                        RecruitSearch.of(title, techStack, job, criteria))));
    }


    @GetMapping("/scraps/users")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> userRecruitScrapList(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_RECRUIT_SCRAP_LIST, recruitService.findUserRecruitScrapList(currentUser.getId())));
    }

    @PostMapping
    @PreAuthorize(roles = {"ROLE_ADMIN"})
    public ResponseEntity<CommonResponseDto> createRecruit(@RequestBody @Valid RecruitSaveRequest request) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_POST_RECRUIT, recruitService.createRecruit(request)));
    }

    @PutMapping("/{id}")
    @PreAuthorize(roles = {"ROLE_ADMIN"})
    public ResponseEntity<CommonResponseDto> updateRecruit(@PathVariable Long id, @RequestBody @Valid RecruitUpdateRequest request) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_PUT_RECRUIT, recruitService.updateRecruit(id, request)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(roles = {"ROLE_ADMIN"})
    public ResponseEntity<CommonResponseDto> deleteRecruit(@PathVariable Long id) {
        recruitService.deleteRecruit(id);
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.NO_CONTENT, SuccessMessage.SUCCESS_DELETE_RECRUIT, id));
    }

    @PutMapping("/{recruitId}/scraps")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> toggleRecruitScrap(@AuthenticationPrincipal CurrentUser currentUser,
                                                                @PathVariable Long recruitId) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_PUT_RECRUIT_SCRAP, recruitService.toggleRecruitScraps(currentUser.getId(), recruitId)));
    }

}
