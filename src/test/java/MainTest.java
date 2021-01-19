import com.yy.annotation.LogAspect;
import com.yy.annotation.TestAnnotation;
import com.yy.snowflake.SFIdGenerator;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;


@ContextConfiguration(locations="classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MainTest {

    @Autowired
    TestAnnotation testAnnotation;

    @Resource(name = "customThreadPool")
    private ExecutorService customThreadPool;

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
}
