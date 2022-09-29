package com.cwnu.BlackIceBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableRetry
@EnableScheduling
public class BlackIceBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackIceBackApplication.class, args);
	}

	// TODO 모든 코드에 주석 추가하기 !!!

}
