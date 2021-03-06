package com.moram.ssafe.dto.notification;

import com.moram.ssafe.domain.notification.Notification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class NotificationRequest {

    @NotBlank(message = "빈 문자열을 포함하지 않습니다")
    @Length(max = 150, message = "150자 이하로 작성해주세요")
    private String message;

    @NotBlank(message = "보낸사람 이름이 없습니다.")
    private String sender;

    @NotNull(message = "User Id가 없습니다")
    private Long userId;

    public Notification toNotification() {
        return Notification.builder()
                .message(message)
                .sender(sender)
                .recUser(userId)
                .build();
    }

}
