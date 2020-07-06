package com.springboot.challenge.service;

import com.springboot.challenge.domain.item.Item;
import com.springboot.challenge.domain.item.ItemRepository;
import com.springboot.challenge.domain.member.Member;
import com.springboot.challenge.domain.member.MemberRepository;
import com.springboot.challenge.domain.order.OrderRepository;
import com.springboot.challenge.domain.order.Orders;
import com.springboot.challenge.domain.orderitem.OrderItem;
import com.springboot.challenge.domain.orderitem.OrderItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Rollback(false)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    EntityManager entityManager;

    @Transactional
    @Test
    public void 상품_구매_기능_동작_확() {
        //given
        Member member = Member.builder()
                .userId("test")
                .password("testPassword")
                .name("dongheon")
                .phone("01012345678")
                .build();
        Item item = Item.builder()
                .name("오프화이트신발")
                .price(500000)
                .build();
        Orders order = new Orders(member);
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem(order, item, 3));
        order.setOrderInfo(orderItemList);

        //when
        memberRepository.save(member);
        itemRepository.save(item);
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItemList);

        entityManager.flush();
        entityManager.clear();

        //then
        Member findMember = memberRepository.findByUserId("test").get();
        assertThat(findMember.getOrdersList().get(0).getOrderItems().get(0).getItem()).isEqualTo(item);
    }
}