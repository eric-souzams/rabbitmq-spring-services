package io.rabbit.checkout.service.imp;

import io.rabbit.checkout.entity.UserEntity;
import io.rabbit.checkout.repository.UserRepository;
import io.rabbit.checkout.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserEntity saveUser(UserEntity user) {
        if (checkUserAlreadyExists(user.getEmail())) throw new RuntimeException("User already exists.");

        return userRepository.save(user);
    }

    @Transactional
    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with provided email"));
    }

    @Transactional
    @Override
    public UserEntity findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with provided id"));
    }

    @Transactional
    @Override
    public boolean checkUserAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
