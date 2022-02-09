package com.moram.ssafe.infrastructure.auth;

import com.moram.ssafe.controller.user.annotation.AuthenticationPrincipal;
import com.moram.ssafe.controller.user.annotation.CurrentUser;
import com.moram.ssafe.utill.HeaderUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
@Slf4j
public class AuthenticationArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AuthenticationPrincipal.class) != null;
    }

    @Override
    public CurrentUser resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                       NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String accessToken = HeaderUtils.getAccessToken(webRequest);
        Claims claims = jwtTokenProvider.getData(accessToken);
        Long userId = claims.get("id", Long.class);
        return new CurrentUser(userId);
    }

}