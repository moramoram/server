package com.moram.ssafe.service.user;

import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.dto.user.UserAuthResponse;
import com.moram.ssafe.dto.user.UserProfileResponse;
import com.moram.ssafe.dto.user.UserUpdateAddAuth;
import com.moram.ssafe.exception.user.DuplicateNicknameException;
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
    public UserProfileResponse updateUserAddAuth(UserUpdateAddAuth request) {
        if (userRepository.existsByNickname(request.getNickname())) {
            throw new DuplicateNicknameException();
        }
        Long id = UserContext.getCurrentUserId();
        User originUser = getUser(id);
        originUser.update(request);
        return UserProfileResponse.from(originUser);
    }

    @Transactional
    public void deleteUser() {
        Long id = UserContext.getCurrentUserId();
        User originUser = getUser(id);
        originUser.deleteUser();
    }

    @Transactional
    public UserAuthResponse userAuthApprove(Long id) {
        User originUser = getUser(id);
        originUser.authUpdate();
        return UserAuthResponse.from(originUser);
    }

    public List<UserAuthResponse> userAuthApproveWait() {
        List<User> users = userRepository.findByAuthCheck(Sort.by(Sort.Direction.DESC, "modifiedDate"), 2);
        return users.stream().map(UserAuthResponse::from).collect(Collectors.toList());
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }


    public List<UserAuthResponse> getUserList() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
        return users.stream().map(UserAuthResponse::from).collect(Collectors.toList());
    }

    public boolean nicknameCheck(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Transactional
    public String nicknameUpdate(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException();
        }
        Long id = UserContext.getCurrentUserId();
        User originUser = getUser(id);
        originUser.nickNameUpdate(nickname);
        return originUser.getNickname();
    }

    @Transactional
    public UserProfileResponse userProfileUpdate(String profileImg) {
        Long id = UserContext.getCurrentUserId();
        User originUser = getUser(id);
        originUser.profileImageUpdate(profileImg);
        return UserProfileResponse.from(originUser);
    }
}
