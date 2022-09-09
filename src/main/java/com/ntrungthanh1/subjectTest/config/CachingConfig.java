package com.ntrungthanh1.subjectTest.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(){
        return (builder) -> builder
                //set time stored of user cache is 90ms
                .withCacheConfiguration("userCache", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(90)));
    }
}
