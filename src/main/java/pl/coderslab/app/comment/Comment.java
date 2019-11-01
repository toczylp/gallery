package pl.coderslab.app.comment;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @Size(max = 160)
    private String content;

    @PrePersist
    public void prePersist() {
        this.created = LocalDate.now();
    }
}
