package com.springboot.challenge.service;

import com.springboot.challenge.domain.item.Category;
import com.springboot.challenge.domain.item.Item;
import com.springboot.challenge.domain.item.ItemRepository;
import com.springboot.challenge.web.dto.ItemResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Page<ItemResponseDto> findAll(Category category, PageRequest pageRequest) {
        if (category == null) {
            return itemRepository.findAll(pageRequest)
                    .map(ItemResponseDto::new);
        }
        return itemRepository.findAllByCategory(category, pageRequest)
                .map(ItemResponseDto::new);
    }

    @Transactional
    public ItemResponseDto findById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id=" + id));
        return new ItemResponseDto(item);
    }

    @Transactional
    public List<ItemResponseDto> itemResponseDtosFindByIdIn(List<Long> ids) {
        List<ItemResponseDto> items = itemRepository.findByIdIn(ids).stream()
                .map(ItemResponseDto::new)
                .collect(Collectors.toList());
        return items;
    }

}
