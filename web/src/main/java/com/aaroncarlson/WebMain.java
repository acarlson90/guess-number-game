package com.aaroncarlson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@SpringBootApplication
public class WebMain {

    public static void main(String[] args) {
        log.info("main() method in WebMain is called...");
        SpringApplication.run(WebMain.class, args);
    }

}
