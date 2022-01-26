package com.moram.ssafe.controller.user.annotation;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JwtPayload {
    private long id;

    public JwtPayload(long id) {
        this.id = id;
    }
}
