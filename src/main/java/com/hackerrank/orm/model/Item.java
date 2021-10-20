package com.hackerrank.orm.model;

import com.hackerrank.orm.enums.ItemStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String itemName;
    private String itemEnteredByUser;
    @CreationTimestamp
    private Timestamp itemEnteredDate;
    private Double itemBuyingPrice;
    private Double itemSellingPrice;
    private Date itemLastModifiedDate = new Date();
    private String itemLastModifiedByUser;
    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus = ItemStatus.AVAILABLE;

    public static ItemStatus getStatus(String itemStatus){
        if(itemStatus.equalsIgnoreCase(ItemStatus.AVAILABLE.name())){
            return ItemStatus.AVAILABLE;
        }else if(itemStatus.equalsIgnoreCase(ItemStatus.SOLD.name())){
            return ItemStatus.SOLD;
        }
        return ItemStatus.AVAILABLE;
    }
}
