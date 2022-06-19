package io.rabbit.checkout.service.imp;

import io.rabbit.checkout.entity.CheckoutEntity;
import io.rabbit.checkout.entity.UserEntity;
import io.rabbit.checkout.enums.CheckoutStatus;
import io.rabbit.checkout.repository.CheckoutRepository;
import io.rabbit.checkout.service.CheckoutService;
import io.rabbit.checkout.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CheckoutServiceImp implements CheckoutService {

    private final CheckoutRepository checkoutRepository;

    private final UserService userService;

    @Override
    public CheckoutEntity startCheckout(CheckoutEntity checkout, String userEmail) {
        UserEntity foundedUser = userService.findUserByEmail(userEmail);
        checkout.setUser(foundedUser);
        checkout.setStatus(CheckoutStatus.PENDING);

        return checkoutRepository.save(checkout);
    }
}
