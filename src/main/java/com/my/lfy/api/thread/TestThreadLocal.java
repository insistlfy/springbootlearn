package com.my.lfy.api.thread;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * TestThreadLocal
 * ThreadLocalMap
 *
 * @author lfy
 * @date 2020/8/9
 **/
@Data
public class TestThreadLocal {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static final Integer SIZE = 5;

    private static ExecutorService executorService = new ThreadPoolExecutor(10, 50, 1000, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {

        // Example one
        TestThreadLocal testThreadLocal = new TestThreadLocal();
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(() -> {
                testThreadLocal.setContext(Thread.currentThread().getName() + "的数据");
                System.out.println("------------------------------");
                String context = testThreadLocal.getContext();
                System.out.println(Thread.currentThread().getName() + ":" + context);
            });
        }
        // 避免造成内存泄露
        threadLocal.remove();

        //Example two
        new ClassA().process();
        //避免造成内存泄露
        UserContextHolder.remove();

        //ThreadLocal不支持继承性
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        // InheritableThreadLocal支持继承
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        stringThreadLocal.set("main");
        inheritableThreadLocal.set("main");
        executorService.execute(() -> {
            System.out.println("ThreadLocal-子线程中的本地变量值：" + stringThreadLocal.get());
            System.out.println("InheritableThreadLocal-子线程中的本地变量值：" + inheritableThreadLocal.get());
        });
        System.out.println("ThreadLocal-主线程中的本地变量值：" + stringThreadLocal.get());
        System.out.println("InheritableThreadLocal-主线程中的本地变量值：" + inheritableThreadLocal.get());
        executorService.shutdown();

    }


    public String getContext() {
        return threadLocal.get();
    }

    public void setContext(String context) {
        threadLocal.set(context);
    }
}

class ClassA {

    public void process() {
        User user = User.builder()
                .name("James")
                .sex("M")
                .build();
        UserContextHolder.holder.set(user);
        new ClassB().process();
    }

}

@Slf4j
class ClassB {

    public void process() {
        User user = UserContextHolder.holder.get();
        log.info("ClassB拿到的用户信息，name={},sex={}。", user.getName(), user.getSex());
        new ClassC().process();
    }

}

@Slf4j
class ClassC {

    public void process() {
        User user = UserContextHolder.holder.get();
        log.info("ClassC拿到的用户信息，name={},sex={}。", user.getName(), user.getSex());
    }

}

class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();

    public static void remove() {
        holder.remove();
    }
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class User {

    private String name;

    private String sex;
}


