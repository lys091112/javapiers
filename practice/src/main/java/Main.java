import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    //    lambdaExecThread();

  }


  // 在非并行的lambda处理中，所有的处理过程都是在一个线程中执行
  public static void lambdaExecThread() {
    List<String> str = new ArrayList<>();
    for (int i = 0; i < 1000000; i++) {
      str.add("hello" + i);
    }

    Set<Long> threadIds = new HashSet<>();
    str.forEach(
        s -> {
          threadIds.add(Thread.currentThread().getId());
          System.out.println(s + ", Thread ---> " + Thread.currentThread().getId());
        });

    Set<Long> threadId2s = new HashSet<>();
    str.stream()
        .map(t -> t + "ha")
        .collect(Collectors.toList())
        .forEach(
            s -> {
              threadId2s.add(Thread.currentThread().getId());
              System.out.println(s + ", Thread " + "---> " + Thread.currentThread().getId());
            });

    Set<Long> threadId3s = new HashSet<>();
    str.parallelStream()
        .map(
            t -> {
              threadId3s.add(Thread.currentThread().getId());
              return t + "ha" + Thread.currentThread().getId();
            })
        .collect(Collectors.toList())
        .forEach(
            s -> System.out.println(s + ", " + "Thread " + "---> " + Thread.currentThread().getId()));
    System.out.println(threadIds);
    System.out.println(threadId2s);
    System.out.println(threadId3s);
  }
}
