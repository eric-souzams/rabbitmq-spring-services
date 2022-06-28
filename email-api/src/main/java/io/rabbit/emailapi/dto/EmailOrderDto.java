package io.rabbit.emailapi.dto;

import io.rabbit.emailapi.enums.CheckoutStatus;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailOrderDto implements Serializable {

    private UUID orderId;

    private String emailTo;

    private CheckoutStatus status;

}
