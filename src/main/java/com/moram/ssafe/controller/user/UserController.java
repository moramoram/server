package com.moram.ssafe.controller.user;

import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @GetMapping
    @PreAuthorize(roles = {"ROLE_USER"})
    public String isNotAdmin() {
        return "어드민 아닐때";
    }

    @GetMapping("/success")
    @PreAuthorize(roles = {"ROLE_ADMIN"})
    public String isAdmin() {
        return "어드민 성공";
    }

    @GetMapping("/all")
    @PreAuthorize(roles = {"ROLE_USER", "ROLE_ADMIN"})
    public String adminOrUser() {
       User user = UserContext.currentUser.get();
       log.info("user id: "+user.getId());
       return "어드민 유저 성공";
    }

}
