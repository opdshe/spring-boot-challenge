package com.springboot.challenge.domain.user;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest extends TestCase {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void User_등록_확인() {
        //given
        User user = User.builder()
                .name("heon")
                .email("opdshe@naver.com")
                .address("경기도 수원시 우만동")
                .phone("01033333333")
                .build();
        userRepository.save(user);

        //when
        List<User> users = userRepository.findAll();
        User findUser = users.get(0);

        //then
        assertThat(user.getName()).isEqualTo(findUser.getName());
        assertThat(user.getAddress()).isEqualTo(findUser.getAddress());
    }
}