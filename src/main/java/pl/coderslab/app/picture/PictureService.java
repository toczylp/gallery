package pl.coderslab.app.picture;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.app.category.Category;
import pl.coderslab.app.category.CategoryService;
import pl.coderslab.app.user.User;
import pl.coderslab.app.user.UserNotFoundException;
import pl.coderslab.app.user.UserService;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PictureService {

    private final int PICTURES_IN_PAGE = 6;
    private final int PUBLIC_FLAG = 1;
    private final PictureRepositoryPageable pictureRepositoryPageable;
    private final PictureRepositoryCustom pictureRepositoryCustom;
    private final UserService userService;

    public void save(String fileName, byte[] pic, Principal principal, int publicFlag, Category category) throws NotCorrectFileUploadException, UserNotFoundException {
        pictureRepositoryCustom.save(fileName, pic, principal, publicFlag, category);
    }

    public Picture findByIdAndIncreaseViewsQty(Long id) {
        Picture picture = pictureRepositoryCustom.findById(id);
        picture.setDirectDisplayQty(picture.getDirectDisplayQty() + 1);
        picture.setEncodedPic(picture.encodePic());
        return picture;
    }

    public void decreaseViewsQtybyOne(Long id) {
        Picture picture = pictureRepositoryCustom.findById(id);
        picture.setDirectDisplayQty(picture.getDirectDisplayQty() - 1);
    }

    public boolean rate(int rate, Long id, String login) throws UserNotFoundException {
        Picture picture = pictureRepositoryCustom.findById(id);
        User userWhoRankedCandidate = userService.findByLogin(login);

        if(!picture.getUserswhoRanked().contains(userWhoRankedCandidate)) {
            picture.setRating((picture.getRating() * picture.getRatesQty() + rate) / (picture.getRatesQty() + 1));
            picture.setRatesQty(picture.getRatesQty() + 1);
            List<User> users = picture.getUserswhoRanked();
            users.add(userWhoRankedCandidate);
            picture.setUserswhoRanked(users);
            return true;
        }
        return false;
    }

    public List<Picture> findAll() {
        return pictureRepositoryCustom.findAll();
    }
    public List<Picture> findAllWithUser() {
        return pictureRepositoryCustom.findAllWithUser();
    }

    public List<Picture> findAllPublicPaginable(int page) {
        Page<Picture> pictures = pictureRepositoryPageable.findAllByPublicFlag(new PageRequest(page - 1, PICTURES_IN_PAGE), PUBLIC_FLAG);
        return getPicturesListPaginable(pictures);
    }

    public List<Picture> findAllPublicByCategoryPaginable(int page, String categoryName) {
        Page<Picture> pictures = pictureRepositoryPageable.findAllByPublicFlagAndCategoryName(new PageRequest(page - 1, PICTURES_IN_PAGE), PUBLIC_FLAG, categoryName);
        return getPicturesListPaginable(pictures);
    }

    public List<Picture> findAllPictureByUserLogin(int page, String login) {
        Page<Picture> pictures = pictureRepositoryPageable.findAllByUserLogin(new PageRequest(page - 1, PICTURES_IN_PAGE), login);
        return getPicturesListPaginable(pictures);
    }

    public List<Picture> findLatestSixPublicPictures() {
        Page<Picture> pictures = pictureRepositoryPageable.findAllByPublicFlagOrderByCreatedDesc(new PageRequest(0, PICTURES_IN_PAGE), PUBLIC_FLAG);
        return getPicturesListPaginable(pictures);
    }

    public List<Picture> findAllPaginable(int page) {

        Page<Picture> pictures = pictureRepositoryPageable.findAll(new PageRequest(page - 1, PICTURES_IN_PAGE));
        return getPicturesListPaginable(pictures);
    }

    public List<Picture> findAllPaginableOrderedByDirectViewsQty(int page) {

        Page<Picture> pictures = pictureRepositoryPageable.findAllByPublicFlagOrderByDirectDisplayQtyDesc(new PageRequest(page - 1, PICTURES_IN_PAGE), PUBLIC_FLAG);
        return getPicturesListPaginable(pictures);
    }
    public List<Picture> findAllPaginableOrderedByRating(int page) {

        Page<Picture> pictures = pictureRepositoryPageable.findAllByPublicFlagOrderByRatingDesc(new PageRequest(page - 1, PICTURES_IN_PAGE), PUBLIC_FLAG);
        return getPicturesListPaginable(pictures);
    }

    public void deletePicture(Long id) {
        pictureRepositoryPageable.deleteById(id);
    }

    public List<Picture> getPicturesListPaginable(Page<Picture> pictures) {
        pictures.stream().forEach(p->Hibernate.initialize(p.getUser()));
        List<Picture> listWithEncodedPictures = pictures.stream().map(p -> {
            Picture picture = new Picture(p.getId(), p.getFileName(), p.getCreated(), p.encodePic(), p.getUser(), p.getPublicFlag(), p.getDirectDisplayQty(), p.getRating());
            return picture;
        }).collect(Collectors.toList());
        return listWithEncodedPictures;

    }

    public int totalPagesNo (int page) {
        return pictureRepositoryPageable.findAll(new PageRequest(page - 1, PICTURES_IN_PAGE)).getTotalPages();
    }
    public int totalPagesNoPublic(int page) {
        return pictureRepositoryPageable.findAllByPublicFlag(new PageRequest(page - 1, PICTURES_IN_PAGE), PUBLIC_FLAG).getTotalPages();
    }

    public int totalPagesNoPublicOfGivenCategory(int page, String category) {
        return pictureRepositoryPageable.findAllByPublicFlagAndCategoryName(new PageRequest(page - 1, PICTURES_IN_PAGE), PUBLIC_FLAG, category).getTotalPages();
    }

    public int totalPagesNoUserGallery(int page, String login) {
        return pictureRepositoryPageable.findAllByUserLogin(new PageRequest(page - 1, PICTURES_IN_PAGE), login).getTotalPages();
    }

    public Map<String, String> getExifInfo(Long id) throws ImageProcessingException, IOException {

        Picture picture = pictureRepositoryCustom.findById(id);
        InputStream targetStream = new ByteArrayInputStream(picture.getPic());
        Metadata metadata = ImageMetadataReader.readMetadata(targetStream);

        HashMap<String, String> exif = new HashMap<>();
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {

                if("Model".equals(tag.getTagName()) || "Image Height".equals(tag.getTagName()) || "Image Width".equals(tag.getTagName()) || "Date/Time".equals(tag.getTagName()) || "F-Number".equals(tag.getTagName())) {
                    exif.put(tag.getTagName(), tag.getDescription());
                }
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }
        return exif;
    }
}
