package com.bright.onlinecoursesplatform;
/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 11/12/2022
 */


import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public class AbstractTest {

    private static PostgreSQLContainer sqlContainer = new PostgreSQLContainer<>("postgres:latest");


    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry propertyRegistry){
        propertyRegistry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        propertyRegistry.add("spring.datasource.username", sqlContainer::getUsername);
        propertyRegistry.add("spring.datasource.password", sqlContainer::getPassword);
    }

    @BeforeAll
    public static void setUp(){
        try {
            sqlContainer.start();
        } catch (Exception ignored) {

        }
    }

}
