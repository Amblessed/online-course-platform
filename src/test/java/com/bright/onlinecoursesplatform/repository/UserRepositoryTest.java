package com.bright.onlinecoursesplatform.repository;

import com.bright.onlinecoursesplatform.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 12/12/2022
 */


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/resources/db/clearAll.sql", "file:src/test/resources/db/insert-values.sql"})
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail() {
        User user = userRepository.findByEmail("user1@gmail.com");
        assertNotNull(user);
        assertEquals("user1@gmail.com", user.getEmail());
    }

    @Test
    void findByNotExistingEmail() {
        User user = userRepository.findByEmail("not.existing@gmail.com");
        assertNull(user);
    }
}