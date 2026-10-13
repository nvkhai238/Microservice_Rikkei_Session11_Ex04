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
class StockWriter {
 private final MedicineRepository repository;
 StockWriter(MedicineRepository repository) { this.repository=repository; }
 @Transactional public SellResponse sell(Long id,int quantity) {
  Medicine medicine=repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Khong tim thay thuoc"));
  if(medicine.stock<quantity) throw new ResponseStatusException(HttpStatus.CONFLICT,"Thuoc da het hang");
  medicine.stock-=quantity;
  return new SellResponse(id,medicine.stock);
 }
}
