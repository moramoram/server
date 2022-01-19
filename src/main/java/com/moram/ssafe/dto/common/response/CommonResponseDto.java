package com.moram.ssafe.dto.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponseDto<T> {

    private int status;
    private String message;
    private T data;

    public CommonResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static CommonResponseDto<?> of(HttpStatus httpStatus, String message) {
        int status = Optional.ofNullable(httpStatus)
                .orElse(HttpStatus.OK)
                .value();
        return new CommonResponseDto<>(status, message);
    }

    public static <T> CommonResponseDto<T> of(HttpStatus httpStatus, String message, T data) {
        int status = Optional.ofNullable(httpStatus)
                .orElse(HttpStatus.OK)
                .value();
        return new CommonResponseDto<>(status, message, data);
    }

    public static CommonResponseDto<?> fail(HttpStatus httpStatus, String message) {
        return new CommonResponseDto<>(httpStatus.value(), message, null);
    }
}
