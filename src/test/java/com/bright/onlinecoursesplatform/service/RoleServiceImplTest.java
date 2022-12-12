package com.bright.onlinecoursesplatform.service;

import com.bright.onlinecoursesplatform.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 11/12/2022
 */

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    void testFindRoleByName() {
        roleService.findRoleByName("Admin");
        verify(roleRepository).findByName(any());
    }

    @Test
    void testCreateRole() {
        roleService.createRole("Admin");
        verify(roleRepository, times(1)).save(any());
    }
}