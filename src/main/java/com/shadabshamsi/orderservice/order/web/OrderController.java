package com.shadabshamsi.orderservice.order.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shadabshamsi.orderservice.order.domain.Order;
import com.shadabshamsi.orderservice.order.domain.OrderService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public Flux<Order> getAllOrders() {
    return this.orderService.getAllOrders();
  }

  @PostMapping
  public Mono<Order> submitOrder(@RequestBody @Valid OrderRequest orderRequest) {
    return orderService.submitOrder(orderRequest.isbn(), orderRequest.quantity());
  }

  @Value("${spring.r2dbc.url}")
  private String v;
  @GetMapping("/hello")
  public String hello() {
    return v;
  }
}
