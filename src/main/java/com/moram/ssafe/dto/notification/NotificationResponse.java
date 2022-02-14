package com.moram.ssafe.dto.notification;

import com.moram.ssafe.domain.notification.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class NotificationResponse {

    private Long notificationId;

    private String message;

    private String sender;

    private boolean readCheck;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public static NotificationResponse from(Notification notification) {
        return new NotificationResponse(
                notification.getId(), notification.getMessage(), notification.getSender(),
                notification.isReadCheck(), notification.getCreatedDate(), notification.getModifiedDate()
        );
    }

}
