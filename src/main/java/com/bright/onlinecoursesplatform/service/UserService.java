package com.bright.onlinecoursesplatform.service;
/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 10/12/2022
 */


import com.bright.onlinecoursesplatform.entity.User;

public interface UserService {

    User findUserByEmail(String email);

    User createUser(String email, String password);

    void assignRoleToUser(String email, String roleName);

}

