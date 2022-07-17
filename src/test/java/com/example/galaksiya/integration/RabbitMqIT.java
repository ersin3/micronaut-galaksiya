package com.example.galaksiya.integration;

import com.example.galaksiya.model.Home;
import com.example.galaksiya.repositories.HomeDao;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@MicronautTest
public class RabbitMqIT extends AbstractionBaseTest{

    @Inject
    @Client("/")
    private HttpClient client;

    @Inject
    private HomeDao homeDao;

    @BeforeEach
    void setUp() {
        homeDao.deleteAll();
    }

    @Test
    public void saveMqHomeTest() {
        //setup
        Home home =  Home.builder()
                .propertyName("EvEge").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();


        //Execute
       String response = client.toBlocking().retrieve(HttpRequest.POST("/homes/addMq",home),String.class);

        //assert
        System.out.println(homeDao.findAll());
        assertEquals("Bşarılı",response);
        assertNotNull(homeDao.findById(home.getId()));
    }

}
