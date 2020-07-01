package com.springboot.challenge.service;

import com.springboot.challenge.domain.user.User;
import com.springboot.challenge.domain.user.UserRepository;
import com.springboot.challenge.web.dto.UserRegisterRequestDto;
import com.springboot.challenge.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long register(UserRegisterRequestDto userRegisterRequestDto) {
        return userRepository.save(userRegisterRequestDto.toEntity()).getId();
    }

    @Transactional
    public UserResponseDto findByMemId(String memId){
        User user = userRepository.findByMemId(memId)
                .orElseThrow(()->new IllegalArgumentException("해당 아이디가 존재하지 않습니다."+memId));
        return new UserResponseDto(user);
    }
}
