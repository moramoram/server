package com.moram.ssafe.service.company;

import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.domain.company.Company;
import com.moram.ssafe.domain.company.CompanyComment;
import com.moram.ssafe.domain.company.CompanyCommentRepository;
import com.moram.ssafe.domain.company.CompanyRepository;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.dto.company.CompanyCommentRequest;
import com.moram.ssafe.dto.company.CompanyCommentResponse;
import com.moram.ssafe.dto.company.CompanyCommentUpdateRequest;
import com.moram.ssafe.exception.auth.UserAuthenticationException;
import com.moram.ssafe.exception.company.CompanyCommentNotFoundException;
import com.moram.ssafe.exception.company.CompanyNotFoundException;
import com.moram.ssafe.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyCommentService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final CompanyCommentRepository companyCommentRepository;

    @Transactional
    public CompanyCommentResponse createComment(CompanyCommentRequest request) {
        Long userId = UserContext.getCurrentUserId();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Company company = companyRepository.findById(request.getCompanyId()).orElseThrow(CompanyNotFoundException::new);
        CompanyComment comment = companyCommentRepository.save(
                CompanyComment.builder()
                        .user(user)
                        .company(company)
                        .content(request.getContent())
                        .build()
        );
        return CompanyCommentResponse.from(comment);
    }

    public List<CompanyCommentResponse> findCommentList(Long companyId) {
        return companyCommentRepository.findByCompanyId(companyId).stream()
                .map(CompanyCommentResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public CompanyCommentResponse updateComment(CompanyCommentUpdateRequest request) {
        Long userId = UserContext.getCurrentUserId();

        CompanyComment comment = companyCommentRepository.findById(request.getCommentId())
                .orElseThrow(CompanyCommentNotFoundException::new);

        validCommentUser(userId, comment.getUser().getId());

        comment.update(request.getContent());

        return CompanyCommentResponse.from(comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        Long userId = UserContext.getCurrentUserId();
        CompanyComment comment = companyCommentRepository.findById(id)
                .orElseThrow(CompanyCommentNotFoundException::new);

        validCommentUser(userId, comment.getUser().getId());

        companyCommentRepository.deleteById(id);
    }

    public void validCommentUser(Long currentUser, Long commentUser) {
        if (currentUser == commentUser) {
            return;
        }
        throw new UserAuthenticationException();
    }
}
