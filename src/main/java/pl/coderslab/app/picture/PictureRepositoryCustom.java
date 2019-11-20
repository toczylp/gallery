package pl.coderslab.app.picture;

import org.springframework.stereotype.Repository;
import pl.coderslab.app.category.Category;
import pl.coderslab.app.user.UserNotFoundException;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Repository
@Transactional
public interface  PictureRepositoryCustom {

    void save(String fileName, byte[] pic, Principal principal, int publicFlag, Category category) throws UserNotFoundException;

    Picture findById(Long id);

    List<Picture> findAll();

    List<Picture> findAllWithUser();
}
