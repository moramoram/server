package com.moram.ssafe.controller.user.annotation;

import com.moram.ssafe.exception.auth.InvalidJwtTokenException;

public class UserContext {

    public static final ThreadLocal<JwtPayload> USER_CONTEXT = new ThreadLocal<>();

    public static Long getCurrentUserId() {
        if (UserContext.USER_CONTEXT.get() != null) {
            return UserContext.USER_CONTEXT.get().getId();
        }
        throw new InvalidJwtTokenException();
    }
}
