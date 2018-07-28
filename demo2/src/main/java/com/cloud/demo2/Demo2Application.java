package com.cloud.demo2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Demo2Application {
	
	@Value("${content}")
	private String content;

	@RequestMapping("/")
	public String index() {
		return "content:" + content;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}
}
