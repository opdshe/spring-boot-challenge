package com.springboot.challenge.domain.item;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
public class ItemDescription {
    @Id @GeneratedValue
    @Column(name = "item_description_id")
    private Long id;

    private String description;
}
