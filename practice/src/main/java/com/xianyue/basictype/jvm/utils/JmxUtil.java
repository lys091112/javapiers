package com.xianyue.basictype.jvm.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author liuhongjun
 * @since 2019-12-18
 */
public class JmxUtil {

  /**
   * java8移除了permanent generation，然后class metadata存储在native
   *
   * memory中，其大小默认是不受限的，可以通过-XX:MaxMetaspaceSize来限制；如果开启了-XX:+UseCompressedOops及-XX:+UseCompressedClassesPointers(默认是开启)，则UseCompressedOops会使用32-bit的offset来代表java
   * object的引用，而UseCompressedClassPointers则使用32-bit的offset来代表64-bit进程中的class
   * pointer；可以使用CompressedClassSpaceSize来设置这块的空间大小
   *
   * 开启了指针压缩，则CompressedClassSpace分配在MaxMetaspaceSize里头，即MaxMetaspaceSize=Compressed Class Space
   * Size + Metaspace area (excluding the Compressed Class Space) Size
   *
   * @return
   */
  public List<Pair<String, MemoryUsage>> getMetaspaceSize() {
        return ManagementFactory.getPlatformMXBeans(MemoryPoolMXBean.class)
            .stream()
            .filter(e -> MemoryType.NON_HEAP == e.getType())
            .filter(e -> e.getName().equals("Metaspace") || e.getName().equals("Compressed Class Space"))
            .map(e -> Pair.of(e.getName(), e.getUsage()))
            .collect(Collectors.toList());
    }

}
