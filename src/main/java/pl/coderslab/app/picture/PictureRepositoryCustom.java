package pl.coderslab.app.picture;

import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

@Repository
@Transactional
public interface  PictureRepositoryCustom {

    void save(String fileName, byte[] pic);

    Picture findById(Long id);

    List<Picture> findAll();
}
