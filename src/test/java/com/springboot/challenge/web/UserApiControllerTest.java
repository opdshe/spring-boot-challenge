package com.springboot.challenge.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.challenge.domain.member.Member;
import com.springboot.challenge.domain.member.MemberRepository;
import com.springboot.challenge.exceptions.AlreadyExistUserIdException;
import com.springboot.challenge.web.dto.MemberRegisterRequestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest {
    @Autowired
    EntityManager entityManager;

    @Autowired
    WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @LocalServerPort
    private int port;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Transactional
    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    private static final MemberRegisterRequestDto registerRequestDto = MemberRegisterRequestDto.builder()
            .name("dongheon")
            .userId("testId")
            .password("testPassword")
            .email("test@naver.com")
            .address("경기도 수원시 우만동")
            .phone("01033333333")
            .build();

    @Transactional
    @Test
    public void 회원가입_동작_확인() throws Exception {
        //given
        String url = "http://localhost:" + port + "/api/v1/register";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(registerRequestDto)))
                .andExpect(status().isOk());

        //then
        List<Member> members = memberRepository.findAll();
        Member findMember = members.get(0);
        assertThat(findMember.getName()).isEqualTo("dongheon");
        assertThat(findMember.getUserId()).isEqualTo("testId");
    }

    @Transactional
    @Test
    public void UserId_중복일_때_예외_발생_확인() throws Exception {
        //given
        String url = "http://localhost:" + port + "/api/v1/register";
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(registerRequestDto)))
                .andExpect(status().isOk());
        MemberRegisterRequestDto sameUserIdRequestDto = MemberRegisterRequestDto.builder()
                .name("anotherName")
                .userId("testId")
                .password("anotherPassword")
                .email("another@naver.com")
                .address("anotherAddress")
                .phone("anotherNumbers")
                .build();

        //when
        assertThatExceptionOfType(AlreadyExistUserIdException.class)
                                          .isThrownBy(()->mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(sameUserIdRequestDto)))
                );
    }
}