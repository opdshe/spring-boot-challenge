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

    @Column(name = "member_user_id")
    private String memId;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_address")
    private String address;

    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = true, columnDefinition = "varchar(255) default 'CUSTOMER'")
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Orders> ordersList = new ArrayList<>();

    @Builder
    public Member(String memId, String password, String name, String address, String email, String phone) {
        this.memId = memId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.role = CUSTOMER;
    }
}
