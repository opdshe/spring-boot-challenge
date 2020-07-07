package com.springboot.challenge.web.dto;

import com.springboot.challenge.domain.member.Role;
import com.springboot.challenge.domain.member.Member;
import com.springboot.challenge.domain.order.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;

    private String userId;

    private String password;

    private String name;

    private String address;

    private String email;

    private String phone;

    private Role role;

    private List<Orders> ordersList;

    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.password = entity.getPassword();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.role = entity.getRole();
        this.ordersList = entity.getOrdersList();
    }
}
