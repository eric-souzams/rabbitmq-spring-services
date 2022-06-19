package io.rabbit.checkout.service;

import io.rabbit.checkout.entity.UserEntity;

import java.util.UUID;

public interface UserService {
    UserEntity saveUser(UserEntity user);

    UserEntity findUserByEmail(String email);

    UserEntity findUserById(UUID id);

    boolean checkUserAlreadyExists(String email);
}
