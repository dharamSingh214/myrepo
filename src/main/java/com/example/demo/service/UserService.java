package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

public interface UserService {
    User register(UserDto userDto);
}
