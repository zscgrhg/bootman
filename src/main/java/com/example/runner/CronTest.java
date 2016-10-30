package com.example.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by THINK on 2016/4/25.
 */
@Component
@Slf4j
public class CronTest{

	@Scheduled(cron = "0/30 * * * * ?")
	public void hello() {
		LocalDateTime localDateTime= LocalDateTime.now();
		log.info("这是一个定时任务,每30秒执行一次- "+localDateTime.toString());
	}
}
