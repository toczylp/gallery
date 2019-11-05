package pl.coderslab.app.picture;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void exifInfo(byte[] pictureAsByteArray) throws ImageProcessingException, IOException {

        InputStream targetStream = new ByteArrayInputStream(pictureAsByteArray);
        Metadata metadata = ImageMetadataReader.readMetadata(targetStream);

/*        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.format("[%s] - %s = %s",
                        directory.getName(), tag.getTagName(), tag.getDescription());
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }*/

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
        for (Map.Entry entry: exif.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }


    }
}
