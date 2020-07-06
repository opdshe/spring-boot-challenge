package com.springboot.challenge.service;

import com.springboot.challenge.domain.item.Item;
import com.springboot.challenge.domain.item.ItemRepository;
import com.springboot.challenge.domain.member.Member;
import com.springboot.challenge.domain.member.MemberRepository;
import com.springboot.challenge.domain.order.OrderRepository;
import com.springboot.challenge.domain.order.Orders;
import com.springboot.challenge.domain.orderitem.OrderItem;
import com.springboot.challenge.domain.orderitem.OrderItemRepository;
import com.springboot.challenge.exceptions.MemberMismatchException;
import com.springboot.challenge.web.dto.DetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.springboot.challenge.web.util.SessionManager.*;

@RequiredArgsConstructor
@Service
public class TransactionService {
    private static final int DEFAULT_ITEM_COUNT = 0;

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Long insert(DetailResponseDto responseDto, HttpSession httpSession) {
        Map<Long, Integer> bag = getBagSessionAttribute(httpSession);
        bag.put(responseDto.getId(), bag.getOrDefault(responseDto.getId(), DEFAULT_ITEM_COUNT) + responseDto.getCount());
        httpSession.setAttribute(BAG_ATTRIBUTE_NAME, bag);
        return responseDto.getId();
    }

    @Transactional
    public Long buy(HttpSession httpSession) {
        String username = getUserSessionAttribute(httpSession).getUsername();
        Map<Long, Integer> bag = getBagSessionAttribute(httpSession);
        Member member = memberRepository.findByUserId(username)
                .orElseThrow(() -> new MemberMismatchException(username));
        List<Item> items = itemRepository.findByIdIn(new ArrayList<>(bag.keySet()));

        Orders order = new Orders(member);
        List<OrderItem> orderItems = items.stream()
                .map(item-> new OrderItem(order, item, bag.get(item.getId())))
                .collect(Collectors.toList());
        order.setOrderInfo(orderItems);

        orderItemRepository.saveAll(orderItems);
        orderRepository.save(order);
        setBagAttributeName(httpSession);
        return order.getId();
    }
}
