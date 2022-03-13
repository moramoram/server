package com.moram.ssafe.controller.user;

import com.moram.ssafe.config.s3.S3Uploader;
import com.moram.ssafe.controller.user.annotation.AuthenticationPrincipal;
import com.moram.ssafe.controller.user.annotation.CurrentUser;
import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.dto.common.response.CommonResponseDto;
import com.moram.ssafe.dto.common.response.SuccessMessage;
import com.moram.ssafe.dto.user.UserNickNameRequest;
import com.moram.ssafe.dto.user.UserProfileImgRequest;
import com.moram.ssafe.dto.user.UserUpdateAddAuth;
import com.moram.ssafe.dto.user.UserUpdateAddAuthFormRequest;
import com.moram.ssafe.service.user.AuthService;
import com.moram.ssafe.service.user.UserService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final S3Uploader s3Uploader;

    @GetMapping("/me")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> getUserProfile(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_USER_PROFILE, userService.getUserProfile(currentUser.getId())));
    }

    @PostMapping("/nickname-check")
    public ResponseEntity<CommonResponseDto> nicknameCheck(@RequestBody @Valid UserNickNameRequest request) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_USER_CHECK_NICKNAME, userService.nicknameCheck(request.getNickname())));
    }

    @PutMapping("/refresh")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> refresh(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_PUT_REFRESH, authService.refreshToken(currentUser.getId())));
    }

    @PutMapping("/nickname")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> userNicknameUpdate(@AuthenticationPrincipal CurrentUser currentUser,
                                                                @RequestBody @Valid UserNickNameRequest request) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_UPDATE_USER_NICKNAME, userService.nicknameUpdate(currentUser.getId(), request.getNickname())));

    }

    @PutMapping("/profile-images")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> userProfileUpdate(@AuthenticationPrincipal CurrentUser currentUser,
                                                               @ModelAttribute @Valid UserProfileImgRequest request) throws IOException {
        String profileImg = s3Uploader.upload(request.getProfileImg(), "static/profile");
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_UPDATE_USER_PROFILE_IMG, userService.userProfileUpdate(currentUser.getId(),profileImg)));

    }

    @DeleteMapping("/profile-images")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> userProfileDelete(@AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_UPDATE_USER_PROFILE_IMG, userService.userProfileDelete(currentUser.getId())));

    }

    @PutMapping
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> updateUserAddAuth(@AuthenticationPrincipal CurrentUser currentUser,
                                                               @ModelAttribute @Valid UserUpdateAddAuthFormRequest request) throws IOException {
        String authImg = s3Uploader.upload(request.getAuthImg(), "static/auth");

        UserUpdateAddAuth addAuth = UserUpdateAddAuth.builder()
                .nickname(request.getNickname())
                .realName(request.getRealName())
                .ordinal(request.getOrdinal())
                .campus(request.getCampus())
                .authImg(authImg)
                .build();

        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.CREATED, SUCCESS_UPDATE_USER_ADD_AUTH, userService.updateUserAddAuth(currentUser.getId(),addAuth)));
    }

    @DeleteMapping
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> deleteUser(@AuthenticationPrincipal CurrentUser currentUser) {
        userService.deleteUser(currentUser.getId());
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.NO_CONTENT, SUCCESS_DELETE_USER));
    }

    @GetMapping
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> getUserList() {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_USER_LIST, userService.getUserList()));

    }

    @GetMapping("/{userId}")
    @PreAuthorize(roles = {"ROLE_ADMIN"})
    public ResponseEntity<CommonResponseDto> findUser(@PathVariable Long userId) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_USER, userService.findUser(userId)));

    }

    @GetMapping("/auth-approve/wait")
    @PreAuthorize(roles = {"ROLE_ADMIN"})
    public ResponseEntity<CommonResponseDto> userAuthApproveWait() {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_WAITING_AUTH_USER, userService.userAuthApproveWait()));

    }

    @PutMapping("/auth-approve/{userId}")
    @PreAuthorize(roles = {"ROLE_ADMIN"})
    public ResponseEntity<CommonResponseDto> userAuthApprove(@PathVariable Long userId) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.CREATED, SUCCESS_SSAFE_AUTH_USER, userService.userAuthApprove(userId)));

    }
}
