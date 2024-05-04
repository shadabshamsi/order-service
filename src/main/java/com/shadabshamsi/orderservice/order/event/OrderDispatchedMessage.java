package com.shadabshamsi.orderservice.order.event;

public record OrderDispatchedMessage(
        Long orderId
) {
}
