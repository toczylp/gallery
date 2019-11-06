package pl.coderslab.app.user;

import lombok.Data;
import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.app.role.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

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

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Role> roles;

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
