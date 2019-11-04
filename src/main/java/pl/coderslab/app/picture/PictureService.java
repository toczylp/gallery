package pl.coderslab.app.picture;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PictureService {

    private final int PICTURES_IN_PAGE = 6;

    private final PictureRepositoryPageable pictureRepositoryPageable;
    private final PictureRepositoryCustom pictureRepositoryCustomImpl;

    public void save(String fileName, byte[] pic) throws NotCorrectFileUploadException {
        pictureRepositoryCustomImpl.save(fileName, pic);
    }

    public Picture findById(Long id) {
        return pictureRepositoryCustomImpl.findById(id);
    }

    public List<Picture> findAll() {
        return pictureRepositoryCustomImpl.findAll();
    }

    public List<Picture> findAllPaginable(int page) {

        Page<Picture> pictures = pictureRepositoryPageable.findAll(new PageRequest(page - 1, PICTURES_IN_PAGE));
        List<Picture> listWithEncodedPictures = pictures.stream().map(p -> {
            Picture picture = new Picture(p.getId(), p.getFileName(), p.getCreated(), p.encodePic());
            return picture;
        }).collect(Collectors.toList());

        return listWithEncodedPictures;
    }

    public int totalPagesNo (int page) {
        return pictureRepositoryPageable.findAll(new PageRequest(page - 1, PICTURES_IN_PAGE)).getTotalPages();
    }








}
