package io.rabbit.creditcard.dto;

import io.rabbit.creditcard.enums.CheckoutStatus;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCheckoutOrderStatusDto implements Serializable {

    private UUID orderId;

    private CheckoutStatus status;

}
