package com.shadabshamsi.orderservice.order.event;

import com.shadabshamsi.orderservice.order.domain.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Configuration
public class OrderFunctions {
    private final static Logger logger = LoggerFactory.getLogger(OrderFunctions.class);

    @Bean
    public Consumer<Flux<OrderDispatchedMessage>> dispatchOrder(OrderService orderService) {
        return orderFlux -> orderService.consumeOrderDispatchedEvent(orderFlux)
                .doOnNext(order -> logger.info("The order with id {} is dispatched", order.id())).subscribe();
    }
}
