package com.shadabshamsi.orderservice;

import com.shadabshamsi.orderservice.order.domain.Order;
import com.shadabshamsi.orderservice.order.domain.OrderService;
import com.shadabshamsi.orderservice.order.domain.OrderStatus;
import com.shadabshamsi.orderservice.order.web.OrderController;
import com.shadabshamsi.orderservice.order.web.OrderRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@WebFluxTest(OrderController.class)
public class OrderControllerWebFluxTests {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private OrderService orderService;

    @Test
    void wheBookNotAvailableThenRejectOrder() {
        var orderRequest = new OrderRequest("1234567890", 3);
        var expectedOrder = OrderService.buildRejectedOrder(orderRequest.isbn(), orderRequest.quantity());

        given(orderService.submitOrder(orderRequest.isbn(), orderRequest.quantity())).willReturn(Mono.just(expectedOrder));

        webClient.post().uri("/orders").bodyValue(orderRequest).exchange().expectStatus().is2xxSuccessful()
                .expectBody(Order.class).value(actualOrder -> {
                    assertThat(actualOrder).isNotNull();
                    assertThat(actualOrder.status()).isEqualTo(OrderStatus.REJECTED);
                });
    }
}
