package com.example.galaksiya.services;

import com.example.galaksiya.config.RabbitMqService;
import com.example.galaksiya.model.Home;
import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;

import java.util.List;
import java.util.Optional;


public interface HomeService {
    Optional<Home> getByIdHome(int homeId);

    List<Home> getAllHome();

    Home saveHome(Home home);

}
