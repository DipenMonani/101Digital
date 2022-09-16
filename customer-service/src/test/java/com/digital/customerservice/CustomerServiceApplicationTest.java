package com.digital.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@EnableFeignClients("com.digital")
public class CustomerServiceApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplicationTest.class, args);
    }
}
