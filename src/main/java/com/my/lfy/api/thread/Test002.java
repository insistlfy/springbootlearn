package com.my.lfy.api.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Test002
 * 假设有这样一种情况，有一个篮子，篮子里只能放一个鸡蛋，A线程专门往篮子里放鸡蛋，如果篮子里有鸡蛋，则一直等到篮子里没鸡蛋，B
 * 线程专门从篮子里取鸡蛋，如果篮子里没鸡蛋，则一直等到篮子里有鸡蛋。这里篮子是一个互斥区，每次放鸡蛋是互斥的，每次取鸡蛋也是互斥的，A线程放鸡蛋，如果这时B线程要取鸡蛋，由于A没有释放锁，B
 * 线程处于等待状态，进入阻塞队列，放鸡蛋之后，要通知B线程取鸡蛋，B线程进入就绪队列，反过来，B线程取鸡蛋，如果A线程要放
 * 鸡蛋，由于B线程没有释放锁，A线程处于等待状态，进入阻塞队列，取鸡蛋之后，要通知A线程放鸡蛋，A线程进入就绪队列。我们希望当篮子里有鸡蛋时，A线程阻塞，B线程就绪，篮子里没鸡蛋时，A线程就绪，B线程阻塞
 *
 * @author lfy
 * @date 2020/5/23
 **/
@Slf4j
public class Test002 {


    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 50, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>()
                , new ThreadFactoryBuilder().setNameFormat("线程通信测试--%d").build());

        log.info("线程池的大小=====>{}.", executor.getPoolSize());
        log.info("线程池核心线程数=====>{}.", executor.getCorePoolSize());
        log.info("线程池最大线程数=====>{}.", executor.getMaximumPoolSize());

        Basket basket = new Basket();
        for (int i = 0; i < 20; i++) {
//            new Thread(new PutAgg(basket)).start();
//            new Thread(new GetEgg(basket)).start();
            executor.execute(new PutAgg(basket));
            executor.execute(new GetEgg(basket));

            executor.execute(()->{
                for (int j = 0; j < 100; j++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("j=========>{}.",j);
                }
            });


        }

        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("线程池的大小=====>{}.", executor.getPoolSize());
            log.info("线程池核心线程数=====>{}.", executor.getCorePoolSize());
            log.info("线程池最大线程数=====>{}.", executor.getMaximumPoolSize());

        }

    }
}


/**
 * 鸡蛋类
 *
 * @author lfy
 * @date 2020/5/23
 **/
@Data
@Slf4j
@ApiModel
class Egg implements Serializable {

    private static final long serialVersionUID = 6047827916457336895L;
    @ApiModelProperty(value = "重量")
    private Double weight;

    @ApiModelProperty(value = "单价")
    private Double price;
}


/**
 * 篮子
 *
 * @author lfy
 * @date 2020/5/23
 **/
@Data
@Slf4j
@ApiModel
class Basket implements Serializable {

    private static final long serialVersionUID = 5174588317006862823L;
    /**
     * 共享资源 鸡蛋
     */
    private volatile List<Egg> eggs = new ArrayList<>();

    /**
     * 获取鸡蛋
     *
     * @return Egg
     */
    public synchronized Egg getEgg() {
        while (eggs.size() == 0) {
            try {
                //队列堵塞
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Egg egg = eggs.get(0);

        //清空篮子
        eggs.clear();

        notify();

        log.info("拿到鸡蛋");
        return egg;
    }

    /**
     * 放入鸡蛋
     *
     * @param egg Egg
     */
    public synchronized void putEgg(Egg egg) {

        while (eggs.size() > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //放入鸡蛋
        eggs.add(egg);

        notify();
        log.info("放入鸡蛋");
    }
}

/**
 * 放鸡蛋线程
 */
class PutAgg implements Runnable {

    private Basket basket;
    private Egg egg = new Egg();

    @Override
    public void run() {
        basket.putEgg(egg);
    }

    public PutAgg(Basket basket) {
        this.basket = basket;
    }
}


/**
 * 取鸡蛋线程
 */
class GetEgg implements Runnable {

    private Basket basket;

    @Override
    public void run() {
        basket.getEgg();
    }

    public GetEgg(Basket basket) {
        this.basket = basket;
    }
}