package pl.coderslab.app.picture;

import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.security.crypto.codec.Base64;
import pl.coderslab.app.comment.Comment;
import pl.coderslab.app.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String fileName;

    @Lob
    @NotEmpty
    private byte[] pic;

    public User getUser() {
        return user;
    }

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "picture", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @NotNull
    private int publicFlag;

    private int directDisplayQty = 0;

    private double rating = 0.0d;

    private int ratesQty = 0;

    @Transient
    private String encodedPic;

    public Picture() {
    }

    public Picture(Long id, String fileName, LocalDate created, String encodedPic, User user, int publicFlag) {
        this.id = id;
        this.fileName = fileName;
        this.created = created;
        this.encodedPic = encodedPic;
        this.publicFlag = publicFlag;
        this.user = user;
    }

    public String encodePic() {
        return new String(Base64.encode(this.pic));
    }

    private LocalDate created;

    @PrePersist
    void prePersist() {
        this.created = LocalDate.now();
    }

}
