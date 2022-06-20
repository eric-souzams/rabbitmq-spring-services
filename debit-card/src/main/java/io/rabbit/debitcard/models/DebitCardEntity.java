package io.rabbit.debitcard.models;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "tb_debit_card_transactions")
public class DebitCardEntity {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

}
