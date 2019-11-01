package pl.coderslab.app.picture;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional
@Repository
public class PictureRepositoryCustomImpl implements PictureRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(String fileName, byte[] pic) {

        Picture picture = new Picture();

        picture.setFileName(fileName);
        picture.prePersist();
        picture.setPic(pic);
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
}
