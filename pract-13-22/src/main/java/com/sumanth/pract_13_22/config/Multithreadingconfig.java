package com.sumanth.pract_13_22.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class Multithreadingconfig {


    @Bean("csvAsyncconfig")
    public Executor asyncTaskExecutor()
    {
        ThreadPoolTaskExecutor executor= new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(15);//min 15 threads should run
        executor.setMaxPoolSize(100);//if threads fill max create 15
        executor.setQueueCapacity(5);//waiting
        executor.setThreadNamePrefix("csvasyncprocessor");
        executor.initialize();
        return executor;
    }
/*
15 threads
15 jobs running -> running
5
16th thread created
 */    @Bean("ImageAsyncconfig")
public Executor asyncImageExecutor()
{
    ThreadPoolTaskExecutor executor= new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(15);//min 15 threads should run
    executor.setMaxPoolSize(100);//if threads fill max create 15
    executor.setQueueCapacity(5);//waiting
    executor.setThreadNamePrefix("Imageconfig");
    executor.initialize();
    return executor;
}

}
