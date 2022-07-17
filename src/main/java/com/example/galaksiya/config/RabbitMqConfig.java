package com.example.galaksiya.config;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.rabbitmq.connect.ChannelInitializer;

import jakarta.inject.Singleton;
import java.io.IOException;


@Singleton
public class RabbitMqConfig extends ChannelInitializer {

    @Override
    public void initialize(Channel channel) throws IOException {
        channel.exchangeDeclare("micronaut", BuiltinExchangeType.DIRECT, true);
        channel.queueDeclare("analytics", true, false, false, null);
        channel.queueBind("analytics", "micronaut", "analytics");
    }
}
