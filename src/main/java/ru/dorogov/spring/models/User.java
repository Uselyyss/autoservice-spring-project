package ru.dorogov.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dorogov.spring.emailValid.ValidEmail;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 15, message = "mast be 2-15")
    private String login;
    @Size(min = 3, message = "must be 3+")
    private String password;

    @Column(name = "first_name")
    @Size(min = 2, max = 16, message = "Must be 2-16")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 3, max = 22, message = "Must be 3-22")
    private String lastName;

    @ValidEmail(message = "Email@.com")
    private String email;

}
