package com.moram.ssafe.config;

import com.moram.ssafe.config.properties.OauthProperties;
import com.moram.ssafe.infrastructure.auth.OauthAdapter;
import com.moram.ssafe.infrastructure.auth.OauthProvider;
import com.moram.ssafe.infrastructure.auth.OauthProviderStore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(OauthProperties.class)
public class OauthConfig {

    private final OauthProperties properties;

    public OauthConfig(OauthProperties properties) {
        this.properties = properties;
    }

    @Bean
    public OauthProviderStore oauthProviderStore() {
        Map<String, OauthProvider> providers = OauthAdapter.getOauthProviders(properties);
        return new OauthProviderStore(providers);
    }
}

