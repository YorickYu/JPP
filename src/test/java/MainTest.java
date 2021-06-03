import com.yy.annotation.TestAnnotation;
import com.yy.async.AsyncTestService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;


@ContextConfiguration(locations="classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MainTest {

    @Autowired
    TestAnnotation testAnnotation;

    @Resource(name = "customThreadPool")
    private ExecutorService customThreadPool;


    @Test
    public void runningBean() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object bean = context.getBean("");
        System.out.println("bean = " + bean);
    }

    @Test
    public void test() throws NoSuchFieldException, InterruptedException {
        // 👌
        for (int i = 0; i < 20; i++) {
            customThreadPool.execute(MainTest::run);
        }
        customThreadPool.shutdown();
        while (!customThreadPool.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("线程还在执行。。。");
        }
        System.out.println("finished");
    }

    private static void run() {
        System.out.println("abcd");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 批量入库
     * 需求：假如有10314条数据（非整数），每次入库节点是100条，在一次for循环中入库所有商品
     */
    @Test
    public void testFor() {
        int batch = 100; // 批量数
        int total = 10014; // 总次数
        int head = total/batch;
        int times = 0;
        for (int i = 1; i <= total; i++) {
            int result = i - batch * times;
            if (i % batch == 0) {
                times++;
                System.out.println(times +"次"); // insert list
            }
            if (i > head*batch) {
                System.out.println(i);
            }
        }


    }

    @Test
    public void testThread() throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(() -> {
        });

        FutureTask futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName()); // Thread-1
            return "12312312";
        });
        Thread t = new Thread(futureTask);
        t.start();
        System.out.println("futureTask.get() = " + futureTask.get());

    }

    @Test
    public void testLinkedList() {
        LinkedList<Object> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.push(2);
        linkedList.add(1, 3);
    }

    @Test
    public void testRetry() {
        int i = 0;
        retry:
        for (;;) {
            i ++;
            if (i == 20) {
                System.out.println("break");
                break retry;
            } else {
                System.out.println("continue");
                continue retry;
            }
        }
    }

    @Resource
    private AsyncTestService asyncTestService;

    @Test
    @SneakyThrows
    public void testAsync() {

        ArrayList<Integer> arrayList = new ArrayList<>();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        CompletableFuture<Integer>[] completableFutureArray = new CompletableFuture[list.size()];
        LocalTime startTime = LocalTime.now();
        for (Integer integer : list) {
            if (integer == 5) {
                continue;
            }
            CompletableFuture<Integer> integerCompletableFuture = asyncTestService.testForLoopFutureTask(integer);
            completableFutureArray[list.indexOf(integer)] = integerCompletableFuture;
        }

        CompletableFuture<Integer>[] futures = removeArrayEmptyElement(completableFutureArray);
        CompletableFuture.allOf(futures).join();
        LocalTime endTime = LocalTime.now();
        Duration between = Duration.between(startTime, endTime);
        System.out.println("测试耗时：" + between.toMillis());
        for (CompletableFuture<Integer> future : futures) {
            Integer integer = future.get();
            arrayList.add(integer);
        }
    }


    private CompletableFuture<Integer>[] removeArrayEmptyElement(CompletableFuture<Integer>[] arr) {
        List<CompletableFuture<Integer>> list = Arrays.asList(arr);
        List<CompletableFuture<Integer>> listNew = new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
            if (list.get(i)!=null){
                listNew.add(list.get(i));
            }
        }
        CompletableFuture<Integer>[] arrayNew = listNew.toArray(new CompletableFuture[listNew.size()]);
        return arrayNew;
    }
}
