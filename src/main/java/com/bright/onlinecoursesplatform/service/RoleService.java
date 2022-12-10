package com.bright.onlinecoursesplatform.service;
/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 10/12/2022
 */


import com.bright.onlinecoursesplatform.entity.Role;

public interface RoleService {

    Role findRoleByName(String roleName);

    Role createRole(String roleName);

}

