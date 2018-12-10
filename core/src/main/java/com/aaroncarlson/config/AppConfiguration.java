package com.aaroncarlson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.aaroncarlson.Game;
import com.aaroncarlson.GameImpl;
import com.aaroncarlson.MessageGenerator;
import com.aaroncarlson.MessageGeneratorImpl;
import com.aaroncarlson.NumberGenerator;
import com.aaroncarlson.NumberGeneratorImpl;

/*
 * Goal: AppConfiguration is used to initialize the container
 * @Configuration - annotation that tells Spring to use this java class instead of an XML file
 * @ComponentScan(basePackages="com.aaroncarlson") - tells Spring in which packages you have annotated classes
 * which should be managed by Spring
 * @Import - annotation allows us for loading bean definitions from another configuration class. This allows for
 * modularized configurations and load bean definitions from multiple configuration classes
 */
@Configuration
@Import(GameConfiguration.class)
@ComponentScan(basePackages = "com.aaroncarlson")
public class AppConfiguration {

    // == bean methods ==
    /*
     * @Bean - indicates that method produces a bean to be managed by Spring container. The bean name by default
     * is the name of the bean method
     * NOTE: Although @Bean annotation does have an attribute called name (ex: @Bean(name="exampleName") it is strongly
     * encouraged to not use Strings (way of naming the bean since it can cause typing mistakes)
     *
     * The below code is commented out as the number generator has the @Component annotation which means the class will
     * be managed by the container. This means the container can automatically autowire the NumberGenerator.
     */
//	@Bean
//	public NumberGenerator numberGenerator() {
//		return new NumberGeneratorImpl();
//	}

//	@Bean
//	public Game game() {
//		return new GameImpl();
//	}

//	@Bean
//	public MessageGenerator messageGenerator() {
//		return new MessageGeneratorImpl();
//	}
}
