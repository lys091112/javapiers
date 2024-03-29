package com.xianyue.basictype.lamabda;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

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

  // 当toMap中的两个key冲突是， 使用最新的，保证不会冲突
  private static void toMap() {
    Map<Integer, Item> items =
        items().stream()
            .collect(Collectors.toMap(Item::getQty, t -> t, (oldV, newV) -> newV));
    System.out.println(JSON.toJSONString(items));
  }


  private static List<Item> items() {
    Item i1 = new Item(1L, "item1", 2, new BigDecimal(3.23));
    Item i2 = new Item(2L, "item2", 3, new BigDecimal(3.23));
    Item i3 = new Item(3L, "item3", 2, new BigDecimal(3.23));
    return ImmutableList.of(i1, i2, i3);

  }

  private static List<List<Item>> buildViews() {
    Item i4 = new Item(4L, "item4", 4, new BigDecimal(3.23));
    Item i5 = new Item(5L, "item5", 3, new BigDecimal(3.23));
    Item i6 = new Item(6L, "item6", 8, new BigDecimal(3.23));
    return ImmutableList.of(items(), ImmutableList.of(i4, i5, i6));
  }

  public static void main(String[] args) {
    System.out.println(findFit());
  }
}
