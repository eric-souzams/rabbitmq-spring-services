package io.rabbit.checkout.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutProcessOrderDto implements Serializable {

    private String message;

    private Date timestamp;
}
