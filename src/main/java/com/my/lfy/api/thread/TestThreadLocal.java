package com.my.lfy.api.thread;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TestThreadLocal
 *
 * @author lfy
 * @date 2020/8/9
 **/
public class TestThreadLocal {

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        /**
         * Example one
         */
        TestThreadLocal testThreadLocal = new TestThreadLocal();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                testThreadLocal.setContext(Thread.currentThread().getName() + "的数据");
                System.out.println("------------------------------");
                String context = testThreadLocal.getContext();
                System.out.println(Thread.currentThread().getName() + ":" + context);
            }).start();
        }


        /**
         * Example two
         */
        new ClassA().process();
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
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class User {

    private String name;

    private String sex;
}


