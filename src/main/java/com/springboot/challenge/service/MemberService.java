package com.springboot.challenge.service;

import com.springboot.challenge.domain.member.MemberRepository;
import com.springboot.challenge.web.dto.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long register(UserRegisterRequestDto userRegisterRequestDto) {
        return memberRepository.save(userRegisterRequestDto.toEntity()).getId();
    }
}
