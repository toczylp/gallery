package pl.coderslab.app.picture;

import com.drew.imaging.ImageProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/picture")
public class PictureController {

    private final PictureService pictureService;
    private final Map<String, String> ALLOWED_PICUTRES_TYPE_BY_TWO_FIRST_BYTES = Map.of(
            "JPEG", "ffd8", "PNG", "8950", "BMP", "424d", "TIFF", "4949"
    );

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("picture", new Picture());
        return "add_picture";
    }

    @PostMapping("/add")
    public String add(@RequestParam("file") MultipartFile file, Model model) {


        String fileName = file.getOriginalFilename();
        try {
            byte[] bytePic = file.getBytes();
            if (!pictureValidate(bytePic[0], bytePic[1])) {
                throw new NotCorrectFileUploadException();
            }
            pictureService.exifInfo(bytePic);
            pictureService.save(fileName, bytePic);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotCorrectFileUploadException e) {
            e.printStackTrace();
            model.addAttribute("error", ALLOWED_PICUTRES_TYPE_BY_TWO_FIRST_BYTES.keySet());
            return "add_picture";
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        }

        return "redirect:add";
    }

    @RequestMapping(value = "/read/{id}")
    public String readPicture(@PathVariable Long id, Model model) {

        Picture picture = pictureService.findById(id);
        model.addAttribute("pic", picture.getEncodedPic());
        return "display_picture";
    }

    @RequestMapping(value = "/read/all/page/{page}")
    public String readAllPictures(Model model, @PathVariable("page") int page) {

        List<Picture> encodedPictures = pictureService.findAllPaginable(page);

        model.addAttribute("pages", pictureService.totalPagesNo(page));
        model.addAttribute("pictures", encodedPictures);
        model.addAttribute("currentPage", page);
        return "display_picture";
    }


    private boolean pictureValidate(byte first, byte second) {

        if (ALLOWED_PICUTRES_TYPE_BY_TWO_FIRST_BYTES.containsValue(Integer.toHexString(first & 0xFF)+Integer.toHexString(second & 0xFF))) {
            return true;
        }
        else return false;
    }
}
