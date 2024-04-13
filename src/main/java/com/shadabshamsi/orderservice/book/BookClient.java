package com.shadabshamsi.orderservice.book;

import java.time.Duration;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import reactor.core.publisher.Mono;

@Component
public class BookClient {
    private static final String BOOKS_ROOT_API = "/books/";
    private final WebClient webClient;

    public BookClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Book> getBookByIsbn(String isbn) {
        return webClient.get().uri(BOOKS_ROOT_API + isbn).retrieve().bodyToMono(Book.class).timeout(Duration.ofSeconds(3), Mono.empty()).onErrorResume(WebClientException.class, e -> Mono.empty());
    }
}
