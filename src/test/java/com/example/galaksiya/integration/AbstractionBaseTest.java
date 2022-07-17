package com.example.galaksiya.integration;

import org.testcontainers.containers.PostgreSQLContainer;

import java.beans.BeanProperty;

public abstract class AbstractionBaseTest {

    static final PostgreSQLContainer POSTGRE_SQL_CONTAINER;

    static  {
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres:latest")
                .withDatabaseName("home")
                .withPassword("password")
                .withUsername("username");

        POSTGRE_SQL_CONTAINER.start();
    }

     public static void dynamicPropertySource(){
       System.setProperty("datasources.default.url",POSTGRE_SQL_CONTAINER.getJdbcUrl());
       System.setProperty("datasources.default.username",POSTGRE_SQL_CONTAINER.getUsername());
       System.setProperty("datasources.default.password",POSTGRE_SQL_CONTAINER.getPassword());
    }

}
