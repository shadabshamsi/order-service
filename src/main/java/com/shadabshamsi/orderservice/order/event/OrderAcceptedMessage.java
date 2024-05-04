package com.shadabshamsi.orderservice.order.event;

public record OrderAcceptedMessage(
        Long orderId
) {
}
