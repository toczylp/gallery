package pl.coderslab.app.comment;

import lombok.Data;
import pl.coderslab.app.picture.Picture;
import pl.coderslab.app.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate created;

    @NotEmpty
    @Size(max = 60)
    private String content;

    @ManyToOne
    private User user;

    @ManyToOne
    private Picture picture;

    @PrePersist
    public void prePersist() {
        this.created = LocalDate.now();
    }
}
