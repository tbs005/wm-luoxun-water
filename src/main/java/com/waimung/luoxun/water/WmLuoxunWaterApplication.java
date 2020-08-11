package com.waimung.luoxun.water;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.waimung.luoxun.water")
public class WmLuoxunWaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(WmLuoxunWaterApplication.class, args);
	}

}
