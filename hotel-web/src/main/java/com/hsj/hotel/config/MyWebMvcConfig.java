package com.hsj.hotel.config;

import com.hsj.hotel.intecepter.LoginInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.UnknownHostException;

@Component
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInteceptor loginInteceptor;
   /* @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(RedisSerializer.json());
        return template;
    }*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInteceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login/**","/api/register/**","/api/logout/**","/login/**");

    }
}
