package com.ZTPAI2023.GoldenOaks;

import com.ZTPAI2023.GoldenOaks.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class GoldenOaksApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoldenOaksApplication.class, args);
	}

}

