package com.moram.ssafe.service.notification;

import com.moram.ssafe.domain.notification.Notification;
import com.moram.ssafe.domain.notification.NotificationRepository;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.dto.notification.NotificationRequest;
import com.moram.ssafe.dto.notification.NotificationResponse;
import com.moram.ssafe.dto.user.UserAuthResponse;
import com.moram.ssafe.exception.auth.UserAuthenticationException;
import com.moram.ssafe.exception.notification.NotificationNotFoundException;
import com.moram.ssafe.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public List<NotificationResponse> findNotificationList(Long userId) {
        return notificationRepository.findByRecUser(userId)
                .stream().map(NotificationResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public NotificationResponse findNotification(Long notificationId, Long userId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(NotificationNotFoundException::new);
        validUser(notification.getRecUser(), userId);
        notification.updateReadCheck();
        return NotificationResponse.from(notification);
    }

    @Transactional
    public NotificationResponse createNotification(NotificationRequest request) {
        Notification notification = notificationRepository.save(request.toNotification());
        return NotificationResponse.from(notification);
    }

    @Transactional
    public void removeNotification(Long notificationId, Long userId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(NotificationNotFoundException::new);
        validUser(notification.getRecUser(), userId);
        notificationRepository.deleteById(notificationId);
    }

    @Transactional
    public UserAuthResponse rejectNotification(NotificationRequest request) {
        User originUser = userRepository.findById(request.getUserId()).orElseThrow(UserNotFoundException::new);

        originUser.authReject();

        notificationRepository.save(request.toNotification());

        return UserAuthResponse.from(originUser);
    }

    @Transactional
    public void removeAllNotification(Long userId) {
        notificationRepository.deleteByUserId(userId);
    }

    public void validUser(Long recUser, Long userId) {
        if (recUser == userId)
            return;
        throw new UserAuthenticationException();
    }

}
