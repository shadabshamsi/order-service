package com.shadabshamsi.orderservice.order.domain;

import org.springframework.stereotype.Service;

import com.shadabshamsi.orderservice.book.Book;
import com.shadabshamsi.orderservice.book.BookClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {
    private final BookClient bookClient;
    private final OrderRepository orderRepository;

    public OrderService(BookClient bookClient, OrderRepository orderRepository) {
        this.bookClient = bookClient;
        this.orderRepository = orderRepository;
    }

    public Flux<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public Mono<Order> submitOrder(String isbn, int quantity) {
        return this.bookClient.getBookByIsbn(isbn).map(book -> buildAcceptedOrder(book, quantity))
                .defaultIfEmpty(buildRejectedOrder(isbn, quantity)).flatMap(orderRepository::save);
        // return Mono.just(buildRejectedOrder(isbn, quantity))
        //         .flatMap(orderRepository::save);
    }

    public static Order buildAcceptedOrder(Book book, int quantity) {
        return Order.of(book.isbn(), book.title() + " - " + book.author(), book.price(), quantity,
                OrderStatus.ACCEPTED);
    }

    public static Order buildRejectedOrder(
            String bookIsbn, int quantity) {
        return Order.of(bookIsbn, null, null, quantity, OrderStatus.REJECTED);
    }
}
