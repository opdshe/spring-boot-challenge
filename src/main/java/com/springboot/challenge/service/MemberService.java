package com.springboot.challenge.service;

import com.springboot.challenge.domain.member.Member;
import com.springboot.challenge.domain.member.MemberRepository;
import com.springboot.challenge.exceptions.MemberMismatchException;
import com.springboot.challenge.web.dto.MemberRegisterRequestDto;
import com.springboot.challenge.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long register(MemberRegisterRequestDto memberRegisterRequestDto) {
        return memberRepository.save(memberRegisterRequestDto.toEntity()).getId();
    }

    @Transactional
    public Member findByUserId(String userId) {
        return memberRepository.findByUserId(userId)
                .orElseThrow(() -> new MemberMismatchException(userId));
    }
}
