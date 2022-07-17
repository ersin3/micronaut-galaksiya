package com.example.galaksiya.config;

import com.example.galaksiya.model.Home;
import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;

@RabbitClient("micronaut")
public interface RabbitMqService {
    @Binding("analytics")
    String  addMq(Home home);
}
