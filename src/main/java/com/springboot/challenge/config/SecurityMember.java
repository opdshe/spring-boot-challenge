package com.springboot.challenge.config;

import com.springboot.challenge.domain.user.Member;
import com.springboot.challenge.domain.user.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SecurityMember extends User {
    private static final String ROLE_PREFIX = "ROLE_";

    public SecurityMember(Member member) {
        super(member.getUserId(), member.getPassword(), makeGrantedAuthority(member.getRole()));
    }

    private static List<GrantedAuthority> makeGrantedAuthority(Role role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
        return grantedAuthorities;
    }
}
