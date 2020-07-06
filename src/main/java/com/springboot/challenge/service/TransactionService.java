package com.springboot.challenge.service;

import com.springboot.challenge.domain.item.ItemRepository;
import com.springboot.challenge.domain.member.Member;
import com.springboot.challenge.domain.member.MemberRepository;
import com.springboot.challenge.domain.order.OrderRepository;
import com.springboot.challenge.domain.orderitem.OrderItemRepository;
import com.springboot.challenge.web.dto.DetailResponseDto;
import com.springboot.challenge.web.util.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.springboot.challenge.web.util.SessionManager.BAG_ATTRIBUTE_NAME;
import static com.springboot.challenge.web.util.SessionManager.getBagSessionAttribute;

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
    public String buy(HttpSession httpSession) {
        String username = SessionManager.getUserSessionAttribute(httpSession).getUsername();
        Member member = memberRepository.findByUserId(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. "));
        Map<Long, Integer> bag = getBagSessionAttribute(httpSession);
        List<Long> itemIds = new ArrayList<>(bag.keySet());
        return null;
    }
}
