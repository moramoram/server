package com.moram.ssafe.domain.user;

public enum SocialType {

    GITHUB("SOCIAL_GITHUB"), GOOGLE("SOCIAL_GOOGLE");

    private String socialName;

    SocialType(String socialName) {
        this.socialName = socialName;
    }
}
