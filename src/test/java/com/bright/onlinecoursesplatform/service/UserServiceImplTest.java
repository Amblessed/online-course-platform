package com.bright.onlinecoursesplatform.service;

import com.bright.onlinecoursesplatform.entity.Role;
import com.bright.onlinecoursesplatform.entity.User;
import com.bright.onlinecoursesplatform.repository.RoleRepository;
import com.bright.onlinecoursesplatform.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 11/12/2022
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private User mockedUser;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    void testFindUserByEmail() {
        userService.findUserByEmail("email@gmail.com");
        verify(userRepository).findByEmail(any());
    }

    @Test
    void testCreateUser() {
        String email = "user@gmail.com";
        String password = "password1";
        User user = new User(email, password);
        userService.createUser(email, password);
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argumentCaptor.capture());

        User capturedUser = argumentCaptor.getValue();
        assertEquals(user, capturedUser);
    }

    @Test
    void testAssignRoleToUser() {
        Role role = new Role();
        role.setRoleId(1L);

        when(userRepository.findByEmail(any())).thenReturn(mockedUser);
        when(roleRepository.findByName(any())).thenReturn(role);

        userService.assignRoleToUser("email@gmail.com", "Admin");
        verify(mockedUser, times(1)).assignRoleToUser(role);
    }
}