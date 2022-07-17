package com.example.galaksiya.services;

import com.example.galaksiya.model.Home;
import com.example.galaksiya.repositories.HomeDao;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

@Slf4j
@Singleton
public class HomeManager implements HomeService {

    private final HomeDao homeDao;

    public HomeManager(HomeDao homeDao) {
        this.homeDao = homeDao;
    }

    @Override
    public Optional<Home> getByIdHome(int homeId) {
        return homeDao.findById(homeId);
    }

    @Override
    public List<Home> getAllHome() {
        return (List<Home>) homeDao.findAll();
    }

    @Override
    public Home saveHome(Home home) {
        return homeDao.save(home);
    }

}
