package io.rabbit.checkout.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

}
