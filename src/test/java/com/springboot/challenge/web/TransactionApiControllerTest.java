package com.springboot.challenge.web;


import com.springboot.challenge.config.SecurityMember;
import com.springboot.challenge.domain.item.Item;
import com.springboot.challenge.domain.item.ItemRepository;
import com.springboot.challenge.domain.member.Member;
import com.springboot.challenge.domain.member.MemberRepository;
import com.springboot.challenge.exceptions.EmptyBagException;
import com.springboot.challenge.web.dto.DetailResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
public class TransactionApiControllerTest {
    @Autowired
    private TransactionApiController transactionApiController;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    HttpSession httpSession;

    @Autowired
    EntityManager entityManager;

    @Transactional
    @AfterEach
    public void cleanUp() {
        httpSession.removeAttribute("bag");
        itemRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    void 장바구니_추가_동작_확인() {
        //given
        Long id = 1L;
        Integer count = 3;
        DetailResponseDto detailResponseDto = new DetailResponseDto();
        detailResponseDto.setId(id);
        detailResponseDto.setCount(count);
        httpSession.setAttribute("bag", new HashMap<Long, Integer>());

        //when
        transactionApiController.insert(detailResponseDto, httpSession);

        //then
        Map<Long, Integer> bag = (Map<Long, Integer>) httpSession.getAttribute("bag");
        assertThat(bag.get(id)).isEqualTo(count);
    }

    @Transactional
    @Test
    void 상품구매_동작_확인() {
        //given
        Member mockMember = Member.builder()
                .userId("test")
                .password("testPassword")
                .name("dongheon")
                .phone("01012345678")
                .build();
        Item mockItem = Item.builder()
                .name("오프화이트신발")
                .price(500000)
                .build();
        itemRepository.save(mockItem);
        memberRepository.save(mockMember);

        Item targetItem = itemRepository.findAll().get(0);
        Member targetMember = memberRepository.findByUserId("test").get();

        Map<Long, Integer> bag = new HashMap<>();
        bag.put(targetItem.getId(), 3);
        httpSession.setAttribute("bag", bag);
        httpSession.setAttribute("user", new SecurityMember(targetMember));

        //when
        transactionApiController.buy(httpSession);

        //then
        Member memberAfterBuy = memberRepository.findByUserId(targetMember.getUserId()).get();
        String itemName = memberAfterBuy.getOrdersList().get(0).getOrderItems().get(0).getItem().getName();
        assertThat(itemName).isEqualTo("오프화이트신발");
    }

    @Test
    void 상품구매_시_장바구니_비어있으면_예외발생() {
        //given
        Member mockMember = Member.builder()
                .userId("test")
                .password("testPassword")
                .name("dongheon")
                .phone("01012345678")
                .build();
        httpSession.setAttribute("user", new SecurityMember(mockMember));

        Map<Long, Integer> bag = new HashMap<>();
        httpSession.setAttribute("bag", bag);

        //then
        assertThatExceptionOfType(EmptyBagException.class)
                .isThrownBy(()->transactionApiController.buy(httpSession));
    }
}