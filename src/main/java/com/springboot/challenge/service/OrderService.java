package com.springboot.challenge.service;

import com.springboot.challenge.domain.member.Member;
import com.springboot.challenge.domain.order.OrderRepository;
import com.springboot.challenge.domain.order.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public List<Orders> findAllByMember(Member member) {
        return orderRepository.findAllByMember(member);
    }
}
