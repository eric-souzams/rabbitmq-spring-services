package io.rabbit.debitcard.models;

import io.rabbit.debitcard.enums.CardType;
import io.rabbit.debitcard.enums.CheckoutStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_debit_card_transactions")
public class DebitCardEntity implements Serializable {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false)
    private OffsetDateTime dateTimeProcessed;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CheckoutStatus ProcessStatus;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String cardName;

    @Column(nullable = false, length = 3)
    private String cardCvv;

    @Column(nullable = false)
    private LocalDate cardExpireDate;

    @Type(type = "uuid-char")
    @Column(nullable = false)
    private UUID orderId;

}
