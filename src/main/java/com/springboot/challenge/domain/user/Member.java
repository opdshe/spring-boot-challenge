package com.springboot.challenge.domain.user;

import com.springboot.challenge.domain.order.Orders;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.springboot.challenge.domain.user.Role.CUSTOMER;

@Getter
@NoArgsConstructor
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_user_id", nullable = false)
    private String userId;

    @Column(name = "member_password", nullable = false)
    private String password;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Column(name = "member_address")
    private String address;

    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false, columnDefinition = "varchar(255) default 'CUSTOMER'")
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Orders> ordersList = new ArrayList<>();

    @Builder
    public Member(String userId, String password, String name, String address, String email, String phone) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.role = CUSTOMER;
    }
}
