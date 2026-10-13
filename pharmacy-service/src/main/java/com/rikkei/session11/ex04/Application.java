package com.rikkei.session11.ex04;

import java.util.concurrent.TimeUnit;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@SpringBootApplication
public class Application {
 public static void main(String[] args) { SpringApplication.run(Application.class,args); }
 @Bean RedissonClient redisson(@Value("${spring.data.redis.host:localhost}") String host,@Value("${spring.data.redis.port:6379}") int port) {
  Config config=new Config(); config.useSingleServer().setAddress("redis://"+host+":"+port); return Redisson.create(config);
 }
}
