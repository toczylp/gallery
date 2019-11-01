package pl.coderslab.app.user;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    @NotEmpty
    private String login;
    @NotEmpty
    private String password;

    @Email
    @NotEmpty
    private String mail;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private LocalDate created;
    @Max(10)
    private int picsCounter;

    @PrePersist
    public void prePersist() {
        this.created = LocalDate.now();
    }

}
