package com.springboot.challenge.domain.order;

import com.springboot.challenge.domain.orderitem.OrderItem;
import com.springboot.challenge.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "order_datetime")
    private LocalDateTime orderDatetime;

    @Builder
    public Orders(Member member) {
        this.member = member;
        this.orderDatetime = LocalDateTime.now();
    }

    public void setOrderInfo(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        this.member.getOrdersList().add(this);
    }
}
