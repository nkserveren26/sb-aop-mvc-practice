package com.luv2code.springboot.thymeleafdemo.component;

import java.time.LocalDateTime;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SampleScheduler {

    @Scheduled(fixedRate = 10000)
    @SchedulerLock(
            name = "sampleScheduler",
            lockAtMostFor = "30s",
            lockAtLeastFor = "5s"
    )
    public void execute() {

        System.out.println(
                "[" + LocalDateTime.now() + "] "
                        + "Scheduler START - "
                        + Thread.currentThread().getName()
        );

        try {
            // 実際の処理を想定して、あえて時間のかかる処理にする
            Thread.sleep(8000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(
                "[" + LocalDateTime.now() + "] "
                        + "Scheduler END   - "
                        + Thread.currentThread().getName()
        );
    }
}
