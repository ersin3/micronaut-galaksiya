package com.example.galaksiya.config;

import com.example.galaksiya.model.Home;
import com.example.galaksiya.repositories.HomeDao;
import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;

@RabbitListener
public class RabbitMqListener {

    private final HomeDao homeDao;

    public RabbitMqListener(HomeDao homeDao) {
        this.homeDao = homeDao;
    }

    @Queue("analytics")
    public void addMq(Home home) {
        this.homeDao.save(home);
    }
}
