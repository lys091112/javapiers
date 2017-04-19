package com.xianyue.basictype.lamabda;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

/**
 */
@AllArgsConstructor
@Getter
public class Item {
    private Long itemId;
    private String name;
    private int qty;
    private BigDecimal price;

    @Override
    public String toString() {
        return super.toString();
//        return String.format("name: %s, qty: %s, price: %d",name,qty,price.intValue());
    }
}
