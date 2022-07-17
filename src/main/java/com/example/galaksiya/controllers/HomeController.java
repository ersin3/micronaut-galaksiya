package com.example.galaksiya.controllers;

import com.example.galaksiya.config.RabbitMqService;
import com.example.galaksiya.model.Home;
import com.example.galaksiya.services.HomeService;
import io.micronaut.http.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller("/homes")
public class HomeController {

    private final HomeService homeService;

    private final RabbitMqService rabbitMqService;


    public HomeController(HomeService homeService, RabbitMqService rabbitMqService) {
        this.homeService = homeService;
        this.rabbitMqService = rabbitMqService;
    }

    @Get("/getAll")
    public List<Home> getAllHome(){
        return this.homeService.getAllHome();
    }

    @Post("/addHome")
    public Home saveHome(@Body Home home){
        return this.homeService.saveHome(home);
    }

    @Post("/addMq")
    public String saveMqHome(@Body Home home){
        rabbitMqService.addMq(home);
        return "Bşarılı";
    }

    @Get("/getByIdHome/{homeId}")
    public Optional<Home> getByIdHome(@PathVariable Integer homeId){
        return this.homeService.getByIdHome(homeId);
    }

}
