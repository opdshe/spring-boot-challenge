package com.springboot.challenge.service;

import com.springboot.challenge.domain.member.Member;
import com.springboot.challenge.domain.member.MemberRepository;
import com.springboot.challenge.domain.order.OrderRepository;
import com.springboot.challenge.domain.order.Orders;
import com.springboot.challenge.exceptions.MemberMismatchException;
import com.springboot.challenge.web.dto.MemberRegisterRequestDto;
import com.springboot.challenge.web.dto.OrderDtoForMyPage;
import com.springboot.challenge.web.util.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<OrderDtoForMyPage> getOrderDtosForMyPage(HttpSession httpSession){
        String userId = SessionManager.getUserSessionAttribute(httpSession).getUsername();
        Member member = this.findByUserId(userId);
        List<Orders> ordersFindByMember = this.findAllByMember(member);
        return ordersFindByMember.stream()
                .map(OrderDtoForMyPage::new)
                .collect(Collectors.toList());
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
