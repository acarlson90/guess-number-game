package com.aaroncarlson.util.thymeleaf;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import lombok.extern.slf4j.Slf4j;

/*
 * GOAL of class: Enable decoupled logic (for Thymeleaf) setting the value for setUseDecoupledLogic to true
 * Usually you would use application.properties to set up properties but the property for template logic
 * doesn't exist (at least for the current version of Spring Boot 2.0.3)
 *
 * Bean - Bean configuration for SpringResourceTemplateResolver (Thymeleaf only), the class is a template
 * resolver that resolves templates using Spring resource resolution mechanism. Overview - this class
 * is used to find the template in the template directory when the controller returns the view name.
 *
 * @Component - annotation means a class with this annotation is scanned by the Spring container which
 * allows the use of @Autowired beans into the class
 */
@Slf4j
@Component
public class DecoupledLogicSetup {

    // == fields ==
    private final SpringResourceTemplateResolver templateResolver;

    // == constructors ==
    public DecoupledLogicSetup(SpringResourceTemplateResolver templateResolver) {
        this.templateResolver = templateResolver;
    }

    // == init ==
    @PostConstruct
    public void init() {
        // enables decoupling thymeleaf template logic
        templateResolver.setUseDecoupledLogic(true);
        log.info("Decoupled Template Logic Enabled");
    }

}
