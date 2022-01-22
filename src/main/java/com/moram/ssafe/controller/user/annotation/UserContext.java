package com.moram.ssafe.controller.user.annotation;

import com.moram.ssafe.domain.user.User;

public class UserContext {
    public static ThreadLocal<User> currentUser = new ThreadLocal<>();
}
