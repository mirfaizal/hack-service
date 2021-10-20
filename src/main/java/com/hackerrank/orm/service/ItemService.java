package com.hackerrank.orm.service;

import com.hackerrank.orm.enums.ItemStatus;
import com.hackerrank.orm.model.Item;
import com.hackerrank.orm.repository.ItemRepository;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ItemService {

    public final ItemRepository itemRepository;
    ItemService(ItemRepository repository){
        itemRepository = repository;
    }

    public Item save(Item item) {
        Item item1 = null;
        try {
            item1 = itemRepository.save(item);
        }catch (Exception e){
            e.printStackTrace();
        }
        return item1;
    }

    public Item getById(String itemId) throws NotFoundException {
        Item item = this.itemRepository.findById(Integer.parseInt(itemId));
        if (item == null) {
            throw new NotFoundException("Resource not found");
        }
        return item;
    }

    public void delete(String itemId) throws NotFoundException {
        Item item = getById(itemId);
        if (item !=null) {
            itemRepository.delete(item);
        } else {
            throw new NotFoundException("Resource not found");
        }

    }

    public void delete() {
        itemRepository.deleteAll();
    }

    public List<Item> getAll() {
        List<Item> items =  itemRepository.findAll();
        return items;
    }

    public List<Item> getByStatusAndModifiedByUser(String itemStatus, String itemEnteredByUser) {
        List<Item> list = null;
        try {
            ItemStatus is = Item.getStatus(itemStatus);
            list = itemRepository.getAllByItemStatusAndItemEnteredByUser(is, itemEnteredByUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<Item> getItemsByPagination(String pageSize, String page, String sortBy) {
        Pageable paging = PageRequest.of(Integer.parseInt(page),Integer.parseInt(pageSize), Sort.by(sortBy));
        Page<Item> item = itemRepository.findAll(paging);
        return item.getContent();
    }
}
