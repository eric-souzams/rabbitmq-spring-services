package io.rabbit.checkout.entity;

import io.rabbit.checkout.enums.CardType;
import io.rabbit.checkout.enums.CheckoutStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_checkout")
public class CheckoutEntity implements Serializable {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false, length = 16)
    private String cardNumber;

    @Column(nullable = false, length = 200)
    private String cardName;

    @Column(nullable = false)
    private LocalDate cardExpireDate;

    @Column(nullable = false, length = 3)
    private String cardCvv;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType cardType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CheckoutStatus status;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckoutEntity that = (CheckoutEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(cardNumber, that.cardNumber) && Objects.equals(cardName, that.cardName) && Objects.equals(cardExpireDate, that.cardExpireDate) && Objects.equals(cardCvv, that.cardCvv) && Objects.equals(amount, that.amount) && cardType == that.cardType && status == that.status && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, cardName, cardExpireDate, cardCvv, amount, cardType, status, user);
    }
}
