package com.moram.ssafe.infrastructure.auth;

import java.util.HashMap;
import java.util.Map;

public class OauthProviderStore {
    private final Map<String, OauthProvider> providers;

    public OauthProviderStore(Map<String, OauthProvider> providers) {
        this.providers = new HashMap<>(providers);
    }

    public OauthProvider findByProviderName(String name) {
        return providers.get(name);
    }

}
