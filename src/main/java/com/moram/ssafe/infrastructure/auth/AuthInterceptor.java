package com.moram.ssafe.infrastructure.auth;

import com.moram.ssafe.controller.user.annotation.AnnotationHandler;
import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.exception.auth.NoTokenException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Optional;

import static com.moram.ssafe.infrastructure.auth.JwtTokenProvider.AUTHORITIES_KEY;
import static com.moram.ssafe.infrastructure.auth.JwtTokenProvider.AUTHORITIES_SPLITTER;


@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_HEADER = "Bearer ";
    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }
        HandlerMethod method = (HandlerMethod) handler;
        Optional<PreAuthorize> preAuthorize = getPreAuthorize(method);
        Optional<String> jwt = resolveToken(request);

        if (preAuthorize.isPresent()) {
            String token = jwt.orElseThrow(NoTokenException::new);
            tokenProvider.validateToken(token);
            return AnnotationHandler.hasAnyRole(getAuthorities(token), preAuthorize.get());
        }
        return true;
    }

    private Optional<String> resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_HEADER)) {
            return Optional.of(bearerToken.substring(TOKEN_HEADER.length()));
        }
        return Optional.empty();
    }

    private String[] getAuthorities(String token) {
        Claims claims = tokenProvider.getData(token);

//        Long userId = claims.get("id", Long.class);
//        User optionalUser = userRepository.findById(userId).orElseThrow(UserAuthenticationException::new);
//        UserContext.currentUser.set(optionalUser);
        String authorities = (String) claims.get(AUTHORITIES_KEY);
//        log.info("userId "+ userId);
//        log.info("getAuthorities " + Arrays.toString(authorities.split(AUTHORITIES_SPLITTER)));
        return authorities.split(AUTHORITIES_SPLITTER);
    }

    private Optional<PreAuthorize> getPreAuthorize(HandlerMethod method) {
        for (Annotation annotation : method.getMethod().getAnnotations()) {
            if (annotation instanceof PreAuthorize) {
                return Optional.of((PreAuthorize) annotation);
            }
        }
        return Optional.empty();
    }
}