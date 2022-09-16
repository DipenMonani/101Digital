package com.digital.merchantservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@EnableFeignClients("com.digital")
public class MerchantServiceApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(MerchantServiceApplicationTest.class, args);
    }
}
