package com.springboot.challenge.domain.order;

import com.springboot.challenge.domain.orderitem.OrderItem;
import com.springboot.challenge.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Builder
    public Orders(Member member) {
        this.member = member;
        this.orderDate = LocalDate.now();
    }

    public void order(Member member, List<OrderItem> orderItems) {

    }
}
