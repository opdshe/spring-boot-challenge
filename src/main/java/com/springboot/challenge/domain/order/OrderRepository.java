package com.springboot.challenge.domain.order;

import com.springboot.challenge.domain.member.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    @EntityGraph(attributePaths={"member"})
    List<Orders> findAll();

    @EntityGraph("Order.withAll")
    List<Orders> findAllByMember(Member member);
}
