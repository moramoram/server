package com.moram.ssafe.domain.user;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("ROLE_ADMIN"),AUTH("ROLE_AUTH"), USER("ROLE_USER");

    private String role;

    Role(String role) {
        this.role = role;
    }
}
