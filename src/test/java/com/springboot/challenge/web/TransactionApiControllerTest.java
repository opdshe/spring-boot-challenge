package com.springboot.challenge.web;


import com.springboot.challenge.web.dto.DetailResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TransactionApiControllerTest {
    @Autowired
    TransactionApiController transactionApiController;

    @Autowired
    HttpSession httpSession;

    @Test
    void 장바구니_추가_동작_확인() {
        //given
        Long id = 1L;
        Integer count = 3;
        DetailResponseDto detailResponseDto = DetailResponseDto.builder()
                .id(id)
                .count(count)
                .build();
        httpSession.setAttribute("bag", new HashMap<Long, Integer>());

        //when
        transactionApiController.insert(detailResponseDto, httpSession);

        //then
        Map<Long, Integer> bag = (Map<Long, Integer>) httpSession.getAttribute("bag");
        assertThat(bag.get(id)).isEqualTo(count);
    }
}