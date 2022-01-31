package com.moram.ssafe.controller.recruit;

import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.dto.common.response.CommonResponseDto;
import com.moram.ssafe.dto.common.response.SuccessMessage;
import com.moram.ssafe.dto.recruit.RecruitSaveRequest;
import com.moram.ssafe.dto.recruit.RecruitUpdateRequest;
import com.moram.ssafe.service.recruit.RecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<CommonResponseDto> findRecruit(@PathVariable Long id) {
        recruitService.addView(id);
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_RECRUIT, recruitService.findRecruit(id)));
    }

    @GetMapping("/benefits")
    public ResponseEntity<CommonResponseDto> findRecruitBenefit(@RequestParam int limit) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_RECRUIT_BENEFIT, recruitService.findRecruitBenefit(limit)));
    }

    @GetMapping("/popularity")
    public ResponseEntity<CommonResponseDto> findByLotsOfScrap(@RequestParam int limit) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_RECRUIT_POPULARITY, recruitService.findByLotsOfScrap(limit)));
    }

    @GetMapping("/latest")
    public ResponseEntity<CommonResponseDto> findRecruitLatest(@RequestParam int limit) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_RECRUIT_LATEST, recruitService.findRecruitLatest(limit)));
    }


    @GetMapping("/scraps/users")
    @PreAuthorize(roles = {"ROLE_USER"})
    public  ResponseEntity<CommonResponseDto> userRecruitScrapList(){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_RECRUIT_SCRAP_LIST, recruitService.findUserRecruitScrapList()));
    }

    @PostMapping
    public ResponseEntity<CommonResponseDto> createRecruit(@RequestBody @Valid RecruitSaveRequest request) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_POST_RECRUIT, recruitService.createRecruit(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponseDto> updateRecruit(@PathVariable Long id, @RequestBody @Valid RecruitUpdateRequest request) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_PUT_RECRUIT, recruitService.updateRecruit(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponseDto> deleteRecruit(@PathVariable Long id) {
        recruitService.deleteRecruit(id);
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.NO_CONTENT, SuccessMessage.SUCCESS_DELETE_RECRUIT, id));
    }

    @PutMapping("/{recruitId}/scraps")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> toggleRecruitScrap(@PathVariable Long recruitId) {

        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_PUT_RECRUIT_SCRAP, recruitService.toggleRecruitScraps(recruitId)));
    }

}
