package my.plugins.handle;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by hcq on 2017/7/18.
 */
public class MyListAndHandle {
    private static boolean begin = false;
    public static void init() {
        if (!begin) {
            new MyListAndHandle();
        }
    }
    private MyListAndHandle() {
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        /**
         * 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
         * 定时消费
         */
        service.scheduleAtFixedRate(new MyListAndHandleRunable(), 10, 5, TimeUnit.SECONDS);
    }
}
