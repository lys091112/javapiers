package com.xianyue.basictype.lamabda;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapGroupbyCode {

  public static void main(String[] args) {
    List<String> list =
        Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");

    Map<String, Long> result =
        list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    System.out.println(result);

    List<Item> items =
        Arrays.asList(
            new Item(1L, "apple", 10, new BigDecimal("9.99")),
            new Item(2L, "banana", 20, new BigDecimal("19.99")),
            new Item(3L, "orang", 10, new BigDecimal("29.99")),
            new Item(4L, "watermelon", 10, new BigDecimal("29.99")),
            new Item(5L, "papaya", 20, new BigDecimal("9.99")),
            new Item(6L, "apple", 10, new BigDecimal("9.99")),
            new Item(7L, "banana", 10, new BigDecimal("19.99")),
            new Item(8L, "apple", 20, new BigDecimal("9.99")));

    Map<Long, Item> maps = items.stream().filter(Objects::nonNull)
            .collect(Collectors.toMap(t -> t.getItemId(), t -> t));

    Map<String, Long> counting =
        items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.counting()));

    System.out.println(counting);
    Map<String, Integer> sum =
        items
            .stream()
            .collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));
    System.out.println(sum);

    Map<BigDecimal, List<Item>> groupingByPrice =
        items.stream().collect(Collectors.groupingBy(Item::getPrice));
    System.out.println(groupingByPrice);

    Map<BigDecimal, Set<String>> groupingNames =
        items
            .stream()
            .collect(
                Collectors.groupingBy(
                    Item::getPrice, Collectors.mapping(Item::getName, Collectors.toSet())));
    System.out.println(groupingNames);

    System.out.println(complexMap(items));
  }

  /**
   * 这个例子针对与该场景不是很好，但如果我们需要item中的多个值做为key的话，
   * 那么可以使用这个方式去处理
   * 当只使用item中的一个值作为key时，可以使用Collectors.toMap(...)
   */
  public static Map<String, Integer> complexMap(List<Item> items) {
    return items.stream().collect(HashMap::new, (map, item) -> {
      map.put(item.getName(), item.getQty());
//      map.put("itemId" + item.getItemId(), item.getPrice().intValue());
    }, HashMap::putAll);
  }

}
