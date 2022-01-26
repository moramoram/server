package com.moram.ssafe.controller.user;

import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.dto.common.response.CommonResponseDto;
import com.moram.ssafe.dto.common.response.SuccessMessage;
import com.moram.ssafe.dto.user.UserUpdateAddAuthRequest;
import com.moram.ssafe.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.moram.ssafe.dto.common.response.SuccessMessage.*;


@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> getUserProfile() {

        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_USER_PROFILE, userService.getUserProfile()));
    }

    @PutMapping
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> updateUserAddAuth (@RequestBody @Valid UserUpdateAddAuthRequest request) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_UPDATE_USER_ADD_AUTH, userService.updateUserAddAuth(request)));
    }


    @PutMapping("/test")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> testUserAddAuth (@RequestBody @Valid UserUpdateAddAuthRequest request) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_UPDATE_USER_ADD_AUTH, userService.updateUserAddAuth(request)));
    }

    @DeleteMapping
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> deleteUser () {
        userService.deleteUser();
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.NO_CONTENT, SUCCESS_DELETE_USER));
    }

    @GetMapping("/auth-approve/wait")
    @PreAuthorize(roles = {"ROLE_ADMIN"})
    public ResponseEntity<CommonResponseDto> userAuthApprove () {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_WAITING_AUTH_USER, userService.userAuthApproveWait()));

    }
}
