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


@RestController
@RequestMapping("/api/medicines")
class SaleController {
 private final SaleService service;
 SaleController(SaleService service) { this.service=service; }
 @PostMapping("/{id}/sell") SellResponse sell(@PathVariable Long id,@Valid @RequestBody SellRequest request) throws InterruptedException { return service.sell(id,request.quantity()); }
}
