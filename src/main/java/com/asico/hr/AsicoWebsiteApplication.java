package com.asico.hr;

import org.slf4j.*;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author mohammad saeid tavana
 * @since 2024
 */
@SpringBootApplication
@EnableAsync
public class AsicoWebsiteApplication implements ApplicationRunner {

	private static Logger LOGGER = LoggerFactory.getLogger(AsicoWebsiteApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(AsicoWebsiteApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOGGER.info("=================== asico hr Application Started ================");
	}
}
