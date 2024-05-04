package com.shadabshamsi.orderservice;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class OrderServiceTestContextInitializer
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        //        TestPropertyValues.of(
        //                "spring.datasource.url=" + postgresql.getJdbcUrl(),
        //                "spring.datasource.username=" + postgresql.getUsername(),
        //                "spring.datasource.password=" + postgresql.getPassword()
        //        ).applyTo(applicationContext);
    }
}
