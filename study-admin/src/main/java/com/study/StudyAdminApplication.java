package com.study;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class StudyAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyAdminApplication.class,args);
    }
}
