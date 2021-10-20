package com.hackerrank.orm.repository;

import com.hackerrank.orm.enums.ItemStatus;
import com.hackerrank.orm.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {
    Item findById(int id);
    List<Item> findAll();
    List<Item> getAllByItemStatusAndItemEnteredByUser(ItemStatus itemStatus, String lastModifiedByUser);
    Page<Item> findAll(Pageable pageable);
}
