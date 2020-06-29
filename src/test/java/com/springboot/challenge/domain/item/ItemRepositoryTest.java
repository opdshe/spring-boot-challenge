package com.springboot.challenge.domain.item;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@Rollback(false)
@SpringBootTest
public class ItemRepositoryTest {
    @Autowired
    EntityManager em;

    @Autowired
    private ItemRepository itemRepository;

    @After
    public void cleanUp(){
        itemRepository.deleteAll();
    }

    @Test
    public void Item_등록_확인() {
        //given
        Item item = Item.builder()
                .name("오프화이트신발")
                .price(1000000)
                .category(Category.SHOES)
                .stockQuantity(5)
                .thumbnailUrl("helloWorld")
                .build();
        itemRepository.save(item);

        //when
        List<Item> findItem = itemRepository.findAll();
        Item savedItem = findItem.get(0);
        System.out.println(savedItem.getSales());

        //then
        assertThat(item.equals(savedItem)).isTrue();
    }

    @Test
    @Transactional
    public void sell동작_확인() {
        //given
        Item item = Item.builder()
                .name("오프화이트신발")
                .price(1000000)
                .category(Category.SHOES)
                .stockQuantity(5)
                .thumbnailUrl("helloWorld")
                .build();
        itemRepository.save(item);

        //when
        List<Item> findItem = itemRepository.findAll();
        Item savedItem = findItem.get(0);
        savedItem.sell();

        em.flush();
        em.clear();

        Item target = itemRepository.findAll().get(0);

        //then
        assertThat(target.getStockQuantity()).isEqualTo(4);
    }

    @Test
    public void 필터링_동작_확왼() {
        //given
        Item shoes = Item.builder()
                .name("오프화이트신발")
                .price(1000000)
                .category(Category.SHOES)
                .stockQuantity(5)
                .thumbnailUrl("helloWorld")
                .build();

        Item outer = Item.builder()
                .name("코트")
                .price(1000000)
                .category(Category.OUTER)
                .stockQuantity(5)
                .thumbnailUrl("helloWorld")
                .build();

        //when
        itemRepository.save(shoes);
        itemRepository.save(outer);
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "sales"));
        List<Item> outers = itemRepository.findAllByCategory(Category.OUTER, pageRequest).getContent();
        Boolean result =outers.stream()
                .map(Item::getCategory)
                .allMatch(Predicate.isEqual(Category.OUTER));

        //then
        assertThat(result).isTrue();
    }
}