package com.springboot.challenge.service;

import com.springboot.challenge.domain.user.Member;
import com.springboot.challenge.domain.user.MemberRepository;
import com.springboot.challenge.web.dto.UserRegisterRequestDto;
import com.springboot.challenge.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long register(UserRegisterRequestDto userRegisterRequestDto) {
        return memberRepository.save(userRegisterRequestDto.toEntity()).getId();
    }

    @Transactional
    public UserResponseDto findByMemId(String userId){
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(()->new IllegalArgumentException("해당 아이디가 존재하지 않습니다."+userId));
        return new UserResponseDto(member);
    }
}
