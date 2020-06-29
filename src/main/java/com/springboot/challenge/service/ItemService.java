package com.springboot.challenge.service;

import com.springboot.challenge.domain.item.Category;
import com.springboot.challenge.domain.item.ItemRepository;
import com.springboot.challenge.web.dto.ItemResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Page<ItemResponseDto> findAll(Category category, PageRequest pageRequest) {
        if (category == null) {
            return itemRepository.findAll(pageRequest)
                    .map(i -> ItemResponseDto.builder()
                            .id(i.getId())
                            .name(i.getName())
                            .price(i.getPrice())
                            .stockQuantity(i.getStockQuantity())
                            .category(i.getCategory())
                            .thumbnail(i.getThumbnail())
                            .sales(i.getSales())
                            .build());
        }
        return itemRepository.findAllByCategory(category, pageRequest)
                .map(i -> ItemResponseDto.builder()
                        .id(i.getId())
                        .name(i.getName())
                        .price(i.getPrice())
                        .stockQuantity(i.getStockQuantity())
                        .category(i.getCategory())
                        .thumbnail(i.getThumbnail())
                        .sales(i.getSales())
                        .build());
    }
}
