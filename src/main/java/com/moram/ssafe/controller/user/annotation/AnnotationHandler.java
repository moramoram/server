package com.moram.ssafe.controller.user.annotation;

import com.moram.ssafe.exception.user.FailToAuthenticationException;

public class AnnotationHandler {
    public static boolean hasAnyRole(String[] roleType,com.moram.ssafe.controller.user.annotation.PreAuthorize preAuthorize) {
        for (String role : roleType) {
            if (isAuthorityIncludedInRoles(role, preAuthorize.roles())) {
                return true;
            }
        }
        throw new FailToAuthenticationException();
    }

    private static boolean isAuthorityIncludedInRoles(String authority, String[] roles) {
        for (String role : roles) {
            if (authority.equals(role)) {
                return true;
            }
        }
        return false;
    }

}
