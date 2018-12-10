package com.aaroncarlson.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.aaroncarlson.annotation.GuessCount;
import com.aaroncarlson.annotation.MaximumNumber;
import com.aaroncarlson.annotation.MinimumNumber;

/*
 * Example of using primitives as beans
 * @Configuration - annotation that tells Spring to use this java class instead of an XML file
 * @PropertySource("classpath:config/game.properties") - tells the Spring container where to load properties
 * and which file to load
 */
@Configuration
@PropertySource("classpath:config/game.properties")
@ComponentScan(basePackages = "com.aaroncarlson")
public class GameConfiguration {

    // == fields ==
    @Value("${game.maximumNumber:1000}")
    private int maximumNumber;
    @Value("${game.minimumNumber:0}")
    private int minimumNumber;
    @Value("${game.guessCount:10}")
    private int guessCount;

    // == bean methods ==
    @Bean
    @MaximumNumber
    public int maximumNumber() {
        return maximumNumber;
    }

    @Bean
    @MinimumNumber
    public int minimumNumber() {
        return minimumNumber;
    }

    @Bean
    @GuessCount
    public int guessCount() {
        return guessCount;
    }
}
