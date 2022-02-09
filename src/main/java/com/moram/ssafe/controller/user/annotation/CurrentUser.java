package com.moram.ssafe.controller.user.annotation;

import lombok.Getter;

@Getter
public class CurrentUser {

    private Long id;

    public CurrentUser(Long id) {
        this.id = id;
    }
}
