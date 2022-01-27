package com.moram.ssafe.service.user;

import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.dto.user.UserProfileResponse;
import com.moram.ssafe.dto.user.UserUpdateAddAuthRequest;
import com.moram.ssafe.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserProfileResponse getUserProfile() {
        Long id = UserContext.getCurrentUserId();
        User user = getUser(id);
        return UserProfileResponse.from(user);
    }

    @Transactional
    public Long updateUserAddAuth(UserUpdateAddAuthRequest request) {
        Long id = UserContext.getCurrentUserId();
        User originUser = getUser(id);
        originUser.update(request);
        return originUser.getId();
    }

    @Transactional
    public void deleteUser() {
        Long id = UserContext.getCurrentUserId();
        User originUser = getUser(id);
        originUser.deleteUser();
    }

    public List<UserProfileResponse> userAuthApproveWait() {
        List<User> users = userRepository.findByAuthCheck(Sort.by(Sort.Direction.DESC, "modifiedDate"),1);
        return users.stream().map(UserProfileResponse::from).collect(Collectors.toList());
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
