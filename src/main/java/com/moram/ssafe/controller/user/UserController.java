package com.moram.ssafe.controller.user;

import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.dto.common.response.CommonResponseDto;
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

    @GetMapping
    @PreAuthorize(roles = {"ROLE_USER"})
    public String isNotAdmin() {
        return "어드민 아닐때";
    }

    @GetMapping("/success")
    @PreAuthorize(roles = {"ROLE_ADMIN"})
    public String isAdmin() {
        return "어드민 성공";
    }

    @GetMapping("/me/{userId}")
    public ResponseEntity<CommonResponseDto> getUserProfile(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_USER_PROFILE, userService.getUserProfile(userId)));
    }

    @PutMapping
    public ResponseEntity<CommonResponseDto> updateUserAddAuth (@RequestBody @Valid UserUpdateAddAuthRequest request) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_UPDATE_USER_ADD_AUTH, userService.updateUserAddAuth(request)));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<CommonResponseDto> deleteUser (@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.NO_CONTENT, SUCCESS_DELETE_USER, userId));
    }

    @GetMapping("/auth-approve")
    public ResponseEntity<CommonResponseDto> userAuthApprove () {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_WAITING_AUTH_USER, userService.userAuthApprove()));

    }
}
