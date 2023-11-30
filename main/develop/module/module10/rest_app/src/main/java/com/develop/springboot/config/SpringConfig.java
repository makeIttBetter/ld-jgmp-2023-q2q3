package com.develop.springboot.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;


/**
 * This class configures Spring beans.
 * <p> It is annotated with {@link Configuration} to indicate that it is a Spring configuration class.
 * <p> It is annotated with {@link ComponentScan} to indicate that it is a Spring component scanning class.
 * <p> It is annotated with {@link EnableTransactionManagement} to indicate that it is a Spring transaction management class.
 * <p> It is annotated with {@link EnableAutoConfiguration} to indicate that it is a Spring auto configuration class.
 * <p> It is annotated with {@link EnableJpaRepositories} to indicate that it is a Spring JPA repositories class.
 */
@Configuration
@ComponentScan(basePackages = {"com.develop.springboot"}) // add other necessary packages
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.develop.springboot.model.repository"})
public class SpringConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * This method configures a template resolver.
     *
     * @return {@link SpringResourceTemplateResolver} configured template resolver
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    /**
     * This method configures a template engine.
     *
     * @return {@link SpringTemplateEngine} configured template engine
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    /**
     * This method configures a view resolver.
     *
     * @return {@link ThymeleafViewResolver} configured view resolver
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }


    /**
     * This method configures a password encoder.
     *
     * @return {@link PasswordEncoder} configured password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt is a good choice for password encryption. It's strong and secure.
        return new BCryptPasswordEncoder();
    }

    /**
     * This method configures an authentication manager.
     *
     * @param authenticationConfiguration - {@link AuthenticationConfiguration} object to configure authentication manager
     * @return {@link AuthenticationManager} configured authentication manager
     * @throws Exception - if an error occurs while configuring authentication manager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
