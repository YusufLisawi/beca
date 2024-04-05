package com.ntt.springsecutest.service;

import com.ntt.springsecutest.dto.UserDto;
import com.ntt.springsecutest.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
