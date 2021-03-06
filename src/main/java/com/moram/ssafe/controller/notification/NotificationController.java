package com.moram.ssafe.controller.notification;

import com.moram.ssafe.controller.user.annotation.AuthenticationPrincipal;
import com.moram.ssafe.controller.user.annotation.CurrentUser;
import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.dto.common.response.CommonResponseDto;
import com.moram.ssafe.dto.common.response.SuccessMessage;
import com.moram.ssafe.dto.notification.NotificationRequest;
import com.moram.ssafe.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findNotificationList(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_NOTIFICATION, notificationService.findNotificationList(currentUser.getId())));
    }

    @GetMapping("/{notificationId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findNotification(@PathVariable Long notificationId,
                                                              @AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SuccessMessage.SUCCESS_GET_NOTIFICATION, notificationService.findNotification(notificationId, currentUser.getId())));
    }

    @PostMapping
    @PreAuthorize(roles = {"ROLE_ADMIN"})
    public ResponseEntity<CommonResponseDto> createNotification(@RequestBody @Valid NotificationRequest notificationRequest) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.CREATED, SuccessMessage.SUCCESS_POST_NOTIFICATION, notificationService.createNotification(notificationRequest)));
    }

    @PostMapping("/rejects")
    @PreAuthorize(roles = {"ROLE_ADMIN"})
    public ResponseEntity<CommonResponseDto> rejectNotification(@RequestBody @Valid NotificationRequest notificationRequest) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.CREATED, SuccessMessage.SUCCESS_REJECT, notificationService.rejectNotification(notificationRequest)));
    }

    @DeleteMapping("/{notificationId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> removeNotification(@PathVariable Long notificationId,
                                                                @AuthenticationPrincipal CurrentUser currentUser) {
        notificationService.removeNotification(notificationId, currentUser.getId());
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.NO_CONTENT, SuccessMessage.SUCCESS_DELETE_NOTIFICATION));
    }

    @DeleteMapping
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> removeAllNotification(@AuthenticationPrincipal CurrentUser currentUser) {
        notificationService.removeAllNotification(currentUser.getId());
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.NO_CONTENT, SuccessMessage.SUCCESS_DELETE_NOTIFICATION));
    }
}
