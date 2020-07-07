package com.springboot.challenge.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.challenge.domain.member.Member;
import com.springboot.challenge.domain.member.MemberRepository;
import com.springboot.challenge.web.dto.MemberRegisterRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberApiControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private MemberRepository repository;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }
    @After
    public void cleanUp(){
        repository.deleteAll();
    }

    @Test
    public void 회원가입_동작_확인() throws Exception {
        //given
        String name = "heon";
        String userId = "test";
        String password ="testPassword";
        String address = "수원";
        String email = "abc@naver.com";
        String phone = "01012345678";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        MemberRegisterRequestDto requestDto = MemberRegisterRequestDto.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .address(address)
                .email(email)
                .phone(phone)
                .build();
        String url = "http://localhost:" + port + "/api/v1/register";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto.toEntity())))
                .andExpect(status().isOk());

        //then
        List<Member> findMembers = repository.findAll();
        assertThat(findMembers.get(0).getName()).isEqualTo(name);
        System.out.println(findMembers.get(0).getRole().toString());
    }
}