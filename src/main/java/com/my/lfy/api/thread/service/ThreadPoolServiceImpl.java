package com.my.lfy.api.thread.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * ThreadPoolServiceImpl
 *
 * @author lfy
 * @date 2020/5/23
 **/
@Slf4j
@Service
public class ThreadPoolServiceImpl {

    @Autowired
    private ExecutorService executorService;

    public String test01(String string) {
        Future<String> future = executorService.submit(() -> {
            log.info("Callable is starting......");
            StringBuilder builder = new StringBuilder();
            builder.append(string).append("============>").append("successful");
            return builder.toString();
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("callable error=======>", e);
        }
        return null;
    }

    public List<BigDecimal> test02() {
        try {
            return getQuotes();
        } catch (InterruptedException | ExecutionException e) {
            log.error("invokeAll========>", e);
        }
        return new ArrayList<>();
    }


    private List<BigDecimal> getQuotes() throws InterruptedException, ExecutionException {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            tasks.add(new Task(new BigDecimal("200"), i));
        }

        List<BigDecimal> totAmount = new ArrayList<>();
        List<Future<BigDecimal>> futures = executorService.invokeAll(tasks, 15, TimeUnit.SECONDS);

        for (Future<BigDecimal> future : futures) {
            totAmount.add(future.get());
        }

        return totAmount;
    }


}

@Slf4j
@AllArgsConstructor
class Task implements Callable<BigDecimal> {

    private final BigDecimal price;

    private final Integer num;

    @Override
    public BigDecimal call() throws Exception {

        BigDecimal totAmount = price.multiply(BigDecimal.valueOf(num));
        log.info("price={},num={},totAmount={}", price, num, totAmount);
        return totAmount;
    }
}



