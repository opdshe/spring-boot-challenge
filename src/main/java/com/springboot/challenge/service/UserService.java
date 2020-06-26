package com.springboot.challenge.service;

import com.springboot.challenge.domain.user.UserRepository;
import com.springboot.challenge.web.dto.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    @Transactional
    public Long register(UserRegisterRequestDto userRegisterRequestDto) {
        return userRepository.save(userRegisterRequestDto.toEntity()).getId();
    }
}
