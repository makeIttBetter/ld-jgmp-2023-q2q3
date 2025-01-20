package com.develop.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Configuration
@EnableAsync
public class CorsConfig implements WebMvcConfigurer {

    @Value("${application.cors.allowed.origins}")
    private String[] allowedOriginUrls;

    /**
     * Configures the task executor for asynchronous processing.
     * <p> It is annotated with {@link Bean} to indicate that it is a Spring bean factory method.
     * <p>
     * ThreadPoolTaskExecutor: A task executor implementation that uses a thread pool.
     * setCorePoolSize(n): Sets the core number of threads.
     * setMaxPoolSize(n): Sets the maximum allowed number of threads.
     * setQueueCapacity(n): Sets the capacity for the task queue.
     * setThreadNamePrefix("Async-"): Sets the prefix for thread names created by this executor.
     * initialize(): Initializes the executor.
     *
     * @return {@link AsyncTaskExecutor} configured task executor for asynchronous processing
     * @see AsyncTaskExecutor - Spring interface that provides an abstraction for executing tasks asynchronously
     */
    @Bean(name = "taskExecutor")
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // core number of threads
        executor.setMaxPoolSize(30); // maximum number of threads
        executor.setQueueCapacity(500); // capacity of the queue
        executor.setKeepAliveSeconds(60); // keep alive time for idle threads
        executor.setThreadNamePrefix("Async-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // handle rejection
        executor.initialize();
        return executor;
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(taskExecutor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        // Ensure no overlap with OAuth2 login path
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("Adding CORS Mappings");
        registry.addMapping("/appi/**")
                .allowedOrigins(allowedOriginUrls)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}