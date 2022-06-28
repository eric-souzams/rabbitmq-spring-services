package io.rabbit.checkout.dto;

import io.rabbit.checkout.enums.CheckoutStatus;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailResultOrderProcessDto implements Serializable {

    private UUID orderId;

    private String emailTo;

    private CheckoutStatus status;

}
