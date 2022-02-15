package com.moram.ssafe.domain.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n where n.recUser = :userId " +
            "and n.readCheck = false order by n.createdDate desc")
    List<Notification> findByRecUser(@Param("userId") Long userId);
}
