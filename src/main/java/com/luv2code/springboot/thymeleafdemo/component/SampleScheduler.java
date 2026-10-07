package com.luv2code.springboot.thymeleafdemo.component;

import java.time.LocalDateTime;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SampleScheduler {

    @Scheduled(fixedDelay = 1000)
    @SchedulerLock(
            name = "sampleScheduler",
            lockAtMostFor = "1m",
            lockAtLeastFor = "0s"
    )
    public void execute() {

        System.out.println(
                "[" + LocalDateTime.now() + "] "
                        + "Scheduler START - "
                        + Thread.currentThread().getName()
        );

        try {
            // 実際の処理を想定して、あえて時間のかかる処理にする
            Thread.sleep(20000);

            // 意図的にエラーを発生させる
            throw new RuntimeException("意図的なテストエラー");

        } catch (RuntimeException | InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(
                    "[" + LocalDateTime.now() + "] "
                            + "エラー発生: " + e.getMessage()
            );
        }

        System.out.println(
                "[" + LocalDateTime.now() + "] "
                        + "Scheduler END   - "
                        + Thread.currentThread().getName()
        );
    }
}
