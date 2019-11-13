package pl.coderslab.app.picture;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import pl.coderslab.app.user.UserNotFoundException;
import pl.coderslab.app.user.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional
@Repository
@RequiredArgsConstructor
public class PictureRepositoryCustomImpl implements PictureRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserService userService;

    @Override
    public void save(String fileName, byte[] pic, Principal principal, int publicFlag) throws UserNotFoundException {

        Picture picture = new Picture();
        picture.setUser(userService.findByLogin(principal.getName()));
        picture.setFileName(fileName);
        picture.prePersist();
        picture.setPic(pic);
        picture.setPublicFlag(publicFlag);
        entityManager.persist(picture);
    }

    @Override
    public Picture findById(Long id) {
        return entityManager.find(Picture.class, id);
    }

    public List<Picture> findAll() {
        Query query = entityManager.createQuery("SELECT p from Picture p");
        ArrayList<Picture> pictures = (ArrayList<Picture>) query.getResultList();
        return pictures;
    }

    @Override
    public List<Picture> findAllWithUser() {
        Query query = entityManager.createQuery("SELECT p from Picture p");
        ArrayList<Picture> pictures = (ArrayList<Picture>) query.getResultList();
        for (Picture p: pictures) {
            Hibernate.initialize(p.getUser());
        }
        return pictures;
    }

}
