package pl.coderslab.app.picture;

import lombok.Data;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.app.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Map;

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

    @NotNull
    private int publicFlag;

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
