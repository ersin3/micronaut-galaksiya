package com.example.galaksiya.controller;

import com.example.galaksiya.config.RabbitMqManager;
import com.example.galaksiya.config.RabbitMqService;
import com.example.galaksiya.model.Home;
import com.example.galaksiya.repositories.HomeDao;
import com.example.galaksiya.services.HomeManager;
import com.example.galaksiya.services.HomeService;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@MicronautTest
public class HomeControllerTest {

    @Inject
    @Client("/")
    private HttpClient client;

    @Inject
    private HomeService homeService;

    @BeforeEach
    void setUp() {

    }

    @MockBean(HomeManager.class)
    public HomeService homeService(){
        return mock(HomeService.class);
    }


    @Test
    public void getAllTest() {
        //setup
        List<Home> homes = new ArrayList<>();
        Home home =  Home.builder()
                .propertyName("EvEge").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        homes.add(home);

        //Execute
        when(homeService.getAllHome()).thenReturn(homes);

        List<Home> response = client.toBlocking().retrieve(HttpRequest.GET("/homes/getAll"),Argument.listOf(Home.class));

        //assert
        assertNotNull(response);
        assertEquals(1,response.size());
        assertEquals("EvEge",response.get(0).getPropertyName());

    }

    @Test
    public void getByIdHomeTest() {
        //setup
        List<Home> homes = new ArrayList<>();
        Home home =  Home.builder()
                .propertyName("EvEge").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();


        //Execute
        when(homeService.getByIdHome(home.getId())).thenReturn(Optional.of(home));

        Home response = client.toBlocking().retrieve(HttpRequest.GET("/homes/getByIdHome/"+ home.getId()),Home.class);

        //assert
        assertNotNull(response);
        assertEquals(response,home);
        assertEquals("EvEge",response.getPropertyName());

    }

    @Test
    public void addHomeTest() {
        //setup
        Home home =  Home.builder()
                .propertyName("EvEge").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();


        //Execute
        when(homeService.saveHome(home)).thenReturn(home);

        Home response = client.toBlocking().retrieve(HttpRequest.POST("/homes/addHome",home),Home.class);


        //assert
        assertNotNull(response);
        assertEquals(response,home);
        assertEquals("EvEge",response.getPropertyName());

    }


}
