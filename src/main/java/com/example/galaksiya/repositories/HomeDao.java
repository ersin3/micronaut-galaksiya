package com.example.galaksiya.repositories;

import com.example.galaksiya.model.Home;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface HomeDao extends JpaRepository<Home,Integer> {

}
