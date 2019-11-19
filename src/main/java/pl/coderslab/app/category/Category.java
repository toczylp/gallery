package pl.coderslab.app.category;

import lombok.Data;
import pl.coderslab.app.picture.Picture;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Size(min = 3, max = 10)
    @NotEmpty
    String name;
    @OneToMany(mappedBy = "category")
    List<Picture> pictures;
}
