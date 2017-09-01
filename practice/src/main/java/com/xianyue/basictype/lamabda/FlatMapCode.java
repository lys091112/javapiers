package com.xianyue.basictype.lamabda;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class FlatMapCode {
  private static Map<Integer, Set<Long>> findFit() {
    List<List<Item>> views = buildViews();
    return views
        .stream()
        .flatMap(Collection::stream) //将两个item融合
        .filter(view -> view.getPrice().longValue() > 0L)
        .collect(
            Collectors.groupingBy(
                Item::getQty, Collectors.mapping(Item::getItemId, Collectors.toSet())));
  }

  private static List<List<Item>> buildViews() {
    Item i1 = new Item(1L, "item1", 2, new BigDecimal(3.23));
    Item i2 = new Item(2L, "item2", 3, new BigDecimal(3.23));
    Item i3 = new Item(3L, "item3", 2, new BigDecimal(3.23));
    Item i4 = new Item(4L, "item4", 4, new BigDecimal(3.23));
    Item i5 = new Item(5L, "item5", 3, new BigDecimal(3.23));
    Item i6 = new Item(6L, "item6", 8, new BigDecimal(3.23));
    return ImmutableList.of(ImmutableList.of(i1, i2, i3), ImmutableList.of(i4, i5, i6));
  }

  public static void main(String[] args) {
    System.out.println(findFit());
  }
}
