package com.moram.ssafe.domain.user;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailOrSocialId(String email, String socialId);

    List<User> findByAuthCheck(Sort modifiedDate, int check);

}
