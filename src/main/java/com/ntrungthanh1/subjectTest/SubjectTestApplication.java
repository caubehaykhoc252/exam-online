package com.ntrungthanh1.subjectTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.ntrungthanh1.subjectTest"})
public class SubjectTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubjectTestApplication.class, args);
    }

}
