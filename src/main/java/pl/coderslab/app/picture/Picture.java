package pl.coderslab.app.picture;

import lombok.Data;
import org.springframework.security.crypto.codec.Base64;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @Transient
    private String encodedPic;

    public Picture() {
    }

    public Picture(Long id, String fileName, LocalDate created, String encodedPic) {
        this.id = id;
        this.fileName = fileName;
        this.created = created;
        this.encodedPic = encodedPic;
    }

    public String encodePic() {
        return new String(Base64.encode(this.pic));
    }

    /*    @Enumerated(EnumType.STRING)
    private Category category;*/

    private LocalDate created;

/*    public enum Category {
        Flora,
        Fauna,
        Sights,
        People,
        Sport,
        Others
    }*/
    @PrePersist
    public void prePersist() {
        this.created = LocalDate.now();
    }

}
