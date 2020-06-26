package com.springboot.challenge.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.challenge.domain.user.User;
import com.springboot.challenge.domain.user.UserRepository;
import com.springboot.challenge.service.UserService;
import com.springboot.challenge.web.dto.UserRegisterRequestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
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
public class UserApiControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private UserRepository repository;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void 회원가입_동작_확인() throws Exception {
        //given
        String name = "heon";
        String address = "수원";
        String email = "abc@naver.com";
        String phone = "01012345678";
        UserRegisterRequestDto requestDto = UserRegisterRequestDto.builder()
                .name(name)
                .address(address)
                .email(email)
                .phone(phone)
                .build();
        String url = "http://localhost:" + port + "/api/v1/user";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<User> findUsers = repository.findAll();
        assertThat(findUsers.get(0).getName()).isEqualTo(name);

    }


}