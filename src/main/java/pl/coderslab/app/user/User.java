package pl.coderslab.app.user;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.app.comment.Comment;
import pl.coderslab.app.picture.Picture;
import pl.coderslab.app.role.Role;
import pl.coderslab.app.user.customValidators.AgeOver18;
import pl.coderslab.app.user.customValidators.UniqueLogin;
import pl.coderslab.app.user.customValidators.UniqueMail;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.List;

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

    @UniqueLogin
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;

    @Email
    @NotEmpty
    @UniqueMail
    private String mail;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @AgeOver18
    private LocalDate dateOfBirth;

    private LocalDate created;

    private LocalDateTime createdLocalDateTime;

    private LocalDate updated;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Picture> pictures;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @ManyToMany(mappedBy = "userswhoRanked", cascade = CascadeType.REMOVE)
    private List<Picture> rankedPicture;

    private int picsCounter;

    @PrePersist
    public void prePersist() {
        this.created = LocalDate.now();
        this.createdLocalDateTime = LocalDateTime.now();

    }
    @PreUpdate
    public void preUpdate() {
        this.updated = LocalDate.now();
    }

}
