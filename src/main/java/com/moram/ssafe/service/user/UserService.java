package com.moram.ssafe.service.user;

import com.moram.ssafe.config.s3.S3Uploader;
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
    private final S3Uploader s3Uploader;

    public UserProfileResponse getUserProfile(Long userId) {
        User user = getUser(userId);
        return UserProfileResponse.from(user);
    }

    @Transactional
    public UserProfileResponse updateUserAddAuth(Long userId, UserUpdateAddAuth request) {
        if (userRepository.existsByNickname(request.getNickname())) {
            throw new DuplicateNicknameException();
        }
        User originUser = getUser(userId);
        originUser.update(request);
        return UserProfileResponse.from(originUser);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User originUser = getUser(userId);
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

    public UserAuthResponse findUser(Long userId) {
        return UserAuthResponse.from(getUser(userId));
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
    public String nicknameUpdate(Long userId, String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException();
        }
        User originUser = getUser(userId);
        originUser.nickNameUpdate(nickname);
        return originUser.getNickname();
    }

    @Transactional
    public UserProfileResponse userProfileUpdate(Long userId, String profileImg) {
        User originUser = getUser(userId);
        originUser.profileImageUpdate(profileImg);
        return UserProfileResponse.from(originUser);
    }

    @Transactional
    public UserProfileResponse userProfileDelete(Long userId) {
        User originUser = getUser(userId);
//        s3Uploader.deleteObject("profile/" + originUser.getProfileImg());
        originUser.profileImageDelete(originUser.getProfileImg());
        return UserProfileResponse.from(originUser);
    }

}
