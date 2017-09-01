package com.xianyue.basictype.lamabda;

import lombok.*;

import java.math.BigDecimal;

/** */
@NoArgsConstructor
@Data
public class Item {
  private Long itemId;
  private String name;
  private int qty;
  private BigDecimal price;

  public Item(Long itemId, String name, int qty, BigDecimal price) {
    this.itemId = itemId;
    this.name = name;
    this.qty = qty;
    this.price = price;
  }

  @Override
  public String toString() {
    return super.toString();
  }

  public static void main(String[] args) {
    Item item = new Item(1L, "hello", 3, new BigDecimal(4));
    System.out.println(item.getPrice());
  }
}
