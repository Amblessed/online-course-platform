package com.bright.onlinecoursesplatform.repository;

import com.bright.onlinecoursesplatform.entity.Role;
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
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;
    @Test
    void testFindByName() {
        String roleName = "Admin";
        Role role = roleRepository.findByName(roleName);
        assertNotNull(role);
        assertEquals(roleName, role.getName());
    }

    @Test
    void testFindNotExistingRole() {
        String roleName = "NewRole";
        Role role = roleRepository.findByName(roleName);
        assertNull(role);
    }
}