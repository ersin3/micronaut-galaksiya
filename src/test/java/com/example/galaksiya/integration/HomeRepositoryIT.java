package com.example.galaksiya.integration;

import com.example.galaksiya.model.Home;
import com.example.galaksiya.repositories.HomeDao;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

@MicronautTest
public class HomeRepositoryIT extends AbstractionBaseTest{

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
    public void getAllTest() {
        //setup
        Home home =  Home.builder()
                .propertyName("EvEge").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        homeDao.save(home);

        //Execute
         List<Home> getAllHome = (List<Home>) homeDao.findAll();

        //assert
        System.out.println(homeDao.findAll());
        assertNotNull(getAllHome);
        assertEquals(1,getAllHome.size());
        assertEquals("EvEge",getAllHome.get(0).getPropertyName());

    }

    @Test
    public void getByIdHomeTest() {
        //setup
        Home home =  Home.builder()
                .propertyName("EvEge").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        homeDao.save(home);

        //Execute
        Optional<Home> getByHome = homeDao.findById(home.getId());

        //assert
        assertNotNull(getByHome);
        assertEquals(getByHome.get(),home);
        assertEquals("EvEge",getByHome.get().getPropertyName());

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
        Home savedHome = homeDao.save(home);


        //assert
        assertNotNull(savedHome);
        assertEquals(savedHome,home);
        assertEquals(homeDao.findById(home.getId()).get().getPropertyName(),savedHome.getPropertyName());
        assertEquals(home.getId(),savedHome.getId());

    }





}
