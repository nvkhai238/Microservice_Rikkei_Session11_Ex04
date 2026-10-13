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


@Service
class SaleService {
 private final RedissonClient redisson;
 private final StockWriter writer;
 SaleService(RedissonClient redisson,StockWriter writer) { this.redisson=redisson;this.writer=writer; }
 SellResponse sell(Long id,int quantity) throws InterruptedException {
  RLock lock=redisson.getLock("lock:medicine:"+id);
  if(!lock.tryLock(3,5,TimeUnit.SECONDS)) throw new ResponseStatusException(HttpStatus.CONFLICT,"Kho dang ban, vui long thu lai");
  try { return writer.sell(id,quantity); }
  finally { if(lock.isHeldByCurrentThread()) lock.unlock(); }
 }
}
