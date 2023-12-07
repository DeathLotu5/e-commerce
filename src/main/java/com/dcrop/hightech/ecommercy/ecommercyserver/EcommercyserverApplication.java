package com.dcrop.hightech.ecommercy.ecommercyserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EcommercyserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommercyserverApplication.class, args);
	}

}
