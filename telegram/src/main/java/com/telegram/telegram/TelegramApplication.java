package com.telegram.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelegramApplication {

	private static final Logger log = LoggerFactory.getLogger(TelegramApplication.class);

	public static void main(String[] args) {
		log.info("uri 1: [{}]: req: url, content", "/telegram/group");
		log.info("uri 2: [{}]: req: url, content", "/telegram/channel");
		SpringApplication.run(TelegramApplication.class, args);

	}

}
