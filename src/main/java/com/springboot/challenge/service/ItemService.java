package com.springboot.challenge.service;

import com.springboot.challenge.domain.item.Category;
import com.springboot.challenge.domain.item.Item;
import com.springboot.challenge.domain.item.ItemRepository;
import com.springboot.challenge.exceptions.MemberMismatchException;
import com.springboot.challenge.web.dto.ItemResponseDto;
import com.springboot.challenge.web.util.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Page<ItemResponseDto> getPageFindByCategory(Category category, PageRequest pageRequest) {
        if (category == null) {
            return itemRepository.findAll(pageRequest)
                    .map(ItemResponseDto::new);
        }
        return itemRepository.findAllByCategory(category, pageRequest)
                .map(ItemResponseDto::new);
    }

    @Transactional
    public ItemResponseDto getITemResponseDtoFindById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new MemberMismatchException(id.toString()));
        return new ItemResponseDto(item);
    }

    @Transactional
    public List<ItemResponseDto> getBagList(HttpSession httpSession) {
        Map<Long, Integer> bag = SessionManager.getBagSessionAttribute(httpSession);
        List<Long> itemIds = new ArrayList<>(bag.keySet());
        List<ItemResponseDto> items = getItemResponseDtosFindByIdIn(itemIds);
        items.forEach(item -> item.setExtraInfo(bag.get(item.getId())));
        return items;
    }

    @Transactional
    public List<ItemResponseDto> getItemResponseDtosFindByIdIn(List<Long> itemIds) {
        List<ItemResponseDto> items = itemRepository.findItemListByIdIn(itemIds).stream()
                .map(ItemResponseDto::new)
                .collect(Collectors.toList());
        return items;
    }
}
