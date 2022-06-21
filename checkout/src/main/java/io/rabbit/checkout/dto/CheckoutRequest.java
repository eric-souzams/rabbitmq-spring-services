package io.rabbit.checkout.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.rabbit.checkout.enums.CardType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest implements Serializable {

    @NotBlank
    private String cardNumber;

    @NotBlank
    private String cardName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate cardExpireDate;

    @NotBlank
    private String cardCvv;

    @NonNull
    private Double amount;

    @NotNull
    private CardType cardType;

    @NotBlank
    private String userId;

}
