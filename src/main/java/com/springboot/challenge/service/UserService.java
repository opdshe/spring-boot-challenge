package com.springboot.challenge.service;

import com.springboot.challenge.domain.member.Member;
import com.springboot.challenge.domain.member.MemberRepository;
import com.springboot.challenge.domain.order.OrderRepository;
import com.springboot.challenge.domain.order.Orders;
import com.springboot.challenge.exceptions.MemberMismatchException;
import com.springboot.challenge.web.dto.MemberRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Long register(MemberRegisterRequestDto memberRegisterRequestDto) {
        return memberRepository.save(memberRegisterRequestDto.toEntity()).getId();
    }

    @Transactional
    public Member findByUserId(String userId) {
        return memberRepository.findByUserId(userId)
                .orElseThrow(() -> new MemberMismatchException(userId));
    }

    @Transactional
    public List<Orders> findAllByMember(Member member) {
        return orderRepository.findAllByMember(member);
    }
}
