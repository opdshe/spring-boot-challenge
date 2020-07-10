package com.springboot.challenge.domain.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAll(Pageable pageable);

    Page<Item> findAllByCategory(Category category, Pageable pageable);

    List<Item> findItemListByIdIn(List<Long> ids);
}
