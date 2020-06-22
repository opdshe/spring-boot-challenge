package com.springboot.challenge.domain.order;

import com.springboot.challenge.domain.user.User;
import com.springboot.challenge.domain.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void Order_등록_확인() {
        //given
        User user = User.builder()
                .name("heon")
                .email("opdshe@naver.com")
                .address("경기도 수원시 우만동")
                .phone("01033333333")
                .build();
        userRepository.save(user);

        Orders order = Orders.builder()
                .user(user)
                .build();
        orderRepository.save(order);

        //when
        List<Orders> orders = orderRepository.findAll();
        Orders findOrder = orders.get(0);
        System.out.println(findOrder.getOrderDate());

        //then
        assertThat(order.getUser().getName()).isEqualTo(findOrder.getUser().getName());
    }
}