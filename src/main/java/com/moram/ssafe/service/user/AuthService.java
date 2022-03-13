package com.moram.ssafe.service.user;

import com.moram.ssafe.domain.notification.Notification;
import com.moram.ssafe.domain.notification.NotificationRepository;
import com.moram.ssafe.domain.user.Role;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.dto.user.LoginResponse;
import com.moram.ssafe.dto.user.auth.RefreshTokenResponse;
import com.moram.ssafe.exception.user.UserNotFoundException;
import com.moram.ssafe.infrastructure.auth.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final OauthProviderStore oauthProviderStore;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final NotificationRepository notificationRepository;

    public LoginResponse login(String providerName, String code) {

        OauthProvider provider = oauthProviderStore.findByProviderName(providerName);

        OauthTokenResponse tokenResponse = getToken(code, provider);

        UserProfile userProfile = getUserProfile(providerName, tokenResponse, provider);

        User user = saveOrUpdate(userProfile);

        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);

        return LoginResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .authCheck(user.getAuthCheck())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public RefreshTokenResponse refreshToken(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return RefreshTokenResponse.builder()
                .accessToken(jwtTokenProvider.createAccessToken(user))
                .refreshToken(jwtTokenProvider.createRefreshToken(user))
                .build();
    }

    public User saveOrUpdate(UserProfile profile) {
        Optional<User> optionalUser = userRepository.findBySocialId(profile.getOauthId());
        if (!optionalUser.isPresent()) {
            User user = userRepository.save(
                    User.builder()
                            .email(profile.getEmail())
                            .roleType(Role.AUTH)
                            .socialId(profile.getOauthId())
                            .nickname("Guest")
                            .build());
            saveNotification(user.getId());
            return user;
        }
        User user = userRepository.findBySocialId(profile.getOauthId())
                .orElseThrow(UserNotFoundException::new);
        return user;
    }

    public void saveNotification(Long recUser) {
        notificationRepository.save(Notification.builder()
                .message("처음 오신 것을 환영해요! ✨")
                .sender("SSAFE 관리자")
                .recUser(recUser)
                .build());
    }

    private OauthTokenResponse getToken(String code, OauthProvider provider) {
        return WebClient.create()
                .post()
                .uri(provider.getTokenUrl())
                .headers(header -> {
                    header.setBasicAuth(provider.getClientId(), provider.getClientSecret());
                    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                })
                .bodyValue(tokenRequest(code, provider))
                .retrieve()
                .bodyToMono(OauthTokenResponse.class)
                .block();
    }

    private MultiValueMap<String, String> tokenRequest(String code, OauthProvider provider) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", provider.getRedirectUrl());
        return formData;
    }

    private UserProfile getUserProfile(String providerName, OauthTokenResponse tokenResponse, OauthProvider provider) {
        Map<String, Object> userAttributes = getUserAttributes(provider, tokenResponse);
        return OauthAttributes.extract(providerName, userAttributes);
    }

    private Map<String, Object> getUserAttributes(OauthProvider provider, OauthTokenResponse tokenResponse) {
        return WebClient.create()
                .get()
                .uri(provider.getUserInfoUrl())
                .headers(header -> header.setBearerAuth(tokenResponse.getAccessToken()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .block();
    }

}
