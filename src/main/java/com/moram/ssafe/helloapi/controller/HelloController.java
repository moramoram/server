package com.moram.ssafe.helloapi.controller;

import com.moram.ssafe.common.error.SafeServerException;
import com.moram.ssafe.common.response.CommonResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.moram.ssafe.common.error.ErrorCode.HELLO_ERROR_MESSAGE;
import static com.moram.ssafe.common.response.SuccessMessage.*;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<CommonResponseDto> hello() {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_HELLO, "hello ssafe")
        );
    }

    @GetMapping("/helloFail")
    public ResponseEntity<Void> helloFail() {
        throw new SafeServerException(HELLO_ERROR_MESSAGE);
    }
}
