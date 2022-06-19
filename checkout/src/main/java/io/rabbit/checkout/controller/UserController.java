package io.rabbit.checkout.controller;

import io.rabbit.checkout.dto.CheckoutResponse;
import io.rabbit.checkout.dto.UserRequest;
import io.rabbit.checkout.dto.UserResponse;
import io.rabbit.checkout.entity.UserEntity;
import io.rabbit.checkout.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest request) {
        UserEntity user = mapper.map(request, UserEntity.class);

        UserEntity createdUser = userService.saveUser(user);

        UserResponse userResponse = mapper.map(createdUser, UserResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") UUID userId) {
        UserEntity foundedUser = userService.findUserById(userId);

        UserResponse userResponse = mapper.map(foundedUser, UserResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @GetMapping(value = "/{id}/orders")
    public ResponseEntity<?> getUserOrders(@PathVariable("id") UUID userId) {
        UserEntity foundedUser = userService.findUserById(userId);

        List<CheckoutResponse> orders = foundedUser.getOrders().stream()
                .map(order -> mapper.map(order, CheckoutResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
}
