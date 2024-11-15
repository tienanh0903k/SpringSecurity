package com.example.shoppapp.mapper;

import com.example.shoppapp.dto.UserDTO;
import com.example.shoppapp.models.User;

public class UserMapper {

    public static UserDTO ConvertUser(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFullname(user.getFullname());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }
}
