package com.hackerrank.orm.controller;

import com.hackerrank.orm.service.ItemService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hackerrank.orm.model.Item;

import java.util.List;

@RestController
@RequestMapping(path = "/app/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    //1. insert POST
    @PostMapping
    @WriteOperation
    public ResponseEntity<Item> saveItem(@RequestBody Item item) {
        Item itemFromDB = this.itemService.save(item);
        return new ResponseEntity(itemFromDB, HttpStatus.CREATED);
    }

    //2. update PUT
    @PutMapping("/{itemId}")
    @WriteOperation
    public ResponseEntity saveItem(@RequestBody Item item,@PathVariable String itemId) {
        Item itemFromDB = null;
        try {
            itemFromDB = this.itemService.getById(itemId);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        if(itemFromDB == null ) return new ResponseEntity(HttpStatus.NOT_FOUND);
        item.setItemId(itemFromDB.getItemId());
        this.itemService.save(item);
        return new ResponseEntity(HttpStatus.OK);
    }

    //3. delete by itemId DELETE
    @DeleteMapping("/{itemId}")
    @WriteOperation
    public ResponseEntity<HttpStatus> deleteById(@PathVariable String itemId) {
        try {
            this.itemService.delete(itemId);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (NotFoundException exception) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    //4. delete all DELETE
    @DeleteMapping
    @WriteOperation
    public ResponseEntity<HttpStatus> deleteAll() {
        this.itemService.delete();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //5. get by itemId GET
    @GetMapping("/{itemId}")
    @ReadOperation
    public ResponseEntity<Item> getById(@PathVariable String itemId) {
        try {
            return new ResponseEntity(this.itemService.getById(itemId), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    //6. get all GET
    @GetMapping
    @ReadOperation
    public ResponseEntity<List<Item>> getById() {
         return new ResponseEntity(this.itemService.getAll(), HttpStatus.OK);
    }

    //7. filters by fields ?itemStatus={status}&itemEnteredByUser={modifiedBy} GET
    @GetMapping(params = {"itemStatus", "itemEnteredByUser"})
    @ReadOperation
    @ResponseBody
    public ResponseEntity<List<Item>> getItemsByStatusAndModifiedByUser(@RequestParam String itemStatus,
                                                                    @RequestParam String itemEnteredByUser) {
        return new ResponseEntity(this.itemService.getByStatusAndModifiedByUser(itemStatus,itemEnteredByUser), HttpStatus.OK);
    }

    //8. select all with sorting and pagination ?pageSize={pageSize}&page={page}&sortBy={sortBy} GET
    // @RequestMapping(value="/app/item", params = {"pageSize", "page","sortBy"}, method = RequestMethod.GET)
    @GetMapping(params = {"pageSize", "page","sortBy"})
    @ReadOperation
    @ResponseBody
    public ResponseEntity<List<Item>> getItemsByPagination(@RequestParam String pageSize,
                                                                   @RequestParam String page,
                                                                   @RequestParam String sortBy) {
        return new ResponseEntity(this.itemService.getItemsByPagination(pageSize,page,sortBy), HttpStatus.OK);
    }
}
