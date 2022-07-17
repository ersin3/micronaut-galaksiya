package com.example.galaksiya.service;

import com.example.galaksiya.model.Home;
import com.example.galaksiya.repositories.HomeDao;
import com.example.galaksiya.services.HomeService;
import io.micronaut.context.annotation.Requires;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest()
public class HomeServiceTest {


    @Inject
    private HomeService homeService;

    @Inject
    private HomeDao homeDao;

    @BeforeEach
    void setUp() {

    }

    @MockBean(HomeDao.class)
    public HomeDao mockHomeDao(){
        return mock(HomeDao.class);
    }

    @Test
    public void getAllTest() {
        //setup
        List<Home> homes = new ArrayList<>();
        Home home =  Home.builder()
                .propertyName("ErSinEv").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        homes.add(home);

        //Execute
        when(homeDao.findAll()).thenReturn(homes);

        //Assert
        assertNotNull(homeService.getAllHome());
        assertEquals(1,homeService.getAllHome().size());
        assertEquals("ErSinEv",homeService.getAllHome().get(0).getPropertyName());

    }

    @Test
    public void getByIdHomeTest() {
        //setup

        Home home =  Home.builder()
                .propertyName("ErSinEv").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();


        //Execute
        when(homeDao.findById(home.getId())).thenReturn(Optional.of(home));

        //Assert
        assertNotNull(homeService.getByIdHome(home.getId()));
        assertEquals(Optional.of(home),homeService.getByIdHome(home.getId()));
        assertEquals("ErSinEv",homeService.getByIdHome(home.getId()).get().getPropertyName());

    }

    @Test
    public void saveHomeTest() {
        //setup

        Home home =  Home.builder()
                .propertyName("ErSinEv").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();


        //Execute
        when(homeDao.save(home)).thenReturn(home);

        //Assert
        assertNotNull(homeService.saveHome(home));
        assertEquals(home,homeService.saveHome(home));
        assertEquals("ErSinEv",homeService.saveHome(home).getPropertyName());

    }





}
