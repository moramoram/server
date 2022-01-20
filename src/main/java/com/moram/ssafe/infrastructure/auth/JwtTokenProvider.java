package com.moram.ssafe.infrastructure.auth;

import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.exception.auth.ExpiredAccessTokenException;
import com.moram.ssafe.exception.auth.InvalidJwtSignatureException;
import com.moram.ssafe.exception.auth.InvalidJwtTokenException;
import com.moram.ssafe.exception.auth.UnsupportedJwtTokenException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;

@Slf4j
@Component
public class JwtTokenProvider {

    protected static final String AUTHORITIES_KEY = "auth";
    protected static final String AUTHORITIES_SPLITTER = ", ";

    @Value("${jwt.access.expire-length}")
    private long accessTokenValidityInMilliseconds;
    @Value("${jwt.refresh.expire-length}")
    private long refreshTokenValidityInMilliseconds;
    @Value("${jwt.token.secret-key:secret-key}")
    private String secretKey;

    public String createAccessToken(User user) {
        return createToken(user, accessTokenValidityInMilliseconds);
    }

    public String createRefreshToken(User user) {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        return createToken(user, refreshTokenValidityInMilliseconds);
    }

    public String createToken(User user, long expireLength) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + expireLength * 1000);
        log.info(user.getRoleType().getRole());
        String authority = user.getRoleType().getRole();
        return Jwts.builder()
                .claim("id", user.getId())
                .claim(AUTHORITIES_KEY, authority)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(validity)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            throw new InvalidJwtSignatureException();
        } catch (ExpiredJwtException e) {
            throw new ExpiredAccessTokenException();
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtTokenException();
        } catch (IllegalArgumentException e) {
            throw new InvalidJwtTokenException();
        }
    }


    public Claims getData(String token) {
        if (validateToken(token)) {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        }
        return null;
    }
}
