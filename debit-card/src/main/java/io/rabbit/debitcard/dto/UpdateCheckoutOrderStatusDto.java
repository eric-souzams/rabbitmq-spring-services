package io.rabbit.debitcard.dto;

import io.rabbit.debitcard.enums.CheckoutStatus;
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
