package io.rabbit.checkout.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.rabbit.checkout.enums.CardType;
import io.rabbit.checkout.enums.CheckoutStatus;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutResponse implements Serializable {

    private UUID id;

    private String cardNumber;

    private String cardName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yyyy")
    private LocalDate cardExpireDate;

    private String cardCvv;

    private Double amount;

    private CardType cardType;

    private CheckoutStatus status;

}
