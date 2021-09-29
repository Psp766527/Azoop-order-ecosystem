package com.rubicon.water.rubiconWater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RubiconWaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RubiconWaterApplication.class, args);
	}

}
