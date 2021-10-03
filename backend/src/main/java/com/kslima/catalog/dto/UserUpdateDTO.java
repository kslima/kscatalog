package com.kslima.catalog.dto;

import com.kslima.catalog.entities.User;
import com.kslima.catalog.services.validations.UserUpdateValid;

@UserUpdateValid
public class UserUpdateDTO extends UserDTO{

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(User entity) {
        super(entity);
    }
}
