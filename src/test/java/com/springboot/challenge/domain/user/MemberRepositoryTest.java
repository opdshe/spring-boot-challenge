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
public class MemberRepositoryTest extends TestCase {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void User_등록_확인() {
        //given
        Member member = Member.builder()
                .name("heon")
                .userId("test")
                .password("testPassword")
                .email("opdshe@naver.com")
                .address("경기도 수원시 우만동")
                .phone("01033333333")
                .build();
        memberRepository.save(member);

        //when
        List<Member> members = memberRepository.findAll();
        Member findMember = members.get(0);

        //then
        assertThat(member.getName()).isEqualTo(findMember.getName());
        assertThat(member.getAddress()).isEqualTo(findMember.getAddress());
    }
}