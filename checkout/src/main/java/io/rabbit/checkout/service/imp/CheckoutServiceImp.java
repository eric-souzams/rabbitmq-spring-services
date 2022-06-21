package io.rabbit.checkout.service.imp;

import io.rabbit.checkout.dto.UpdateStatusDto;
import io.rabbit.checkout.entity.CheckoutEntity;
import io.rabbit.checkout.entity.UserEntity;
import io.rabbit.checkout.enums.CheckoutStatus;
import io.rabbit.checkout.repository.CheckoutRepository;
import io.rabbit.checkout.service.CheckoutService;
import io.rabbit.checkout.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Service
public class CheckoutServiceImp implements CheckoutService {

    private final CheckoutRepository checkoutRepository;

    private final UserService userService;

    @Transactional
    @Override
    public CheckoutEntity startCheckout(CheckoutEntity checkout, String userId) {
        UUID convertedUserId = UUID.fromString(userId);
        UserEntity foundedUser = userService.findUserById(convertedUserId);
        checkout.setUser(foundedUser);
        checkout.setStatus(CheckoutStatus.PENDING);

        return checkoutRepository.save(checkout);
    }

    @Transactional
    @Override
    public void updateStatus(UpdateStatusDto updateStatusDto) {
        CheckoutEntity foundedCheckout = findCheckoutById(updateStatusDto.getId());
        foundedCheckout.setStatus(updateStatusDto.getStatus());
    }

    @Transactional(readOnly = true)
    @Override
    public CheckoutEntity findCheckoutById(UUID id) {
        return checkoutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Checkout order not found."));
    }

}
