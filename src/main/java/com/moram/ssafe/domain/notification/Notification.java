package com.moram.ssafe.domain.notification;

import com.moram.ssafe.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_notification")
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", updatable = false)
    private Long id;

    private String message;

    private String sender;

    private Long recUser;

    private boolean readCheck;

    @Builder
    public Notification(String message, String sender, Long recUser) {
        this.message = message;
        this.sender = sender;
        this.recUser = recUser;
        this.readCheck = false;
    }

    public void updateReadCheck() {
        this.readCheck = true;
    }

}
