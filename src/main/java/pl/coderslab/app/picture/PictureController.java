package pl.coderslab.app.picture;

import com.drew.imaging.ImageProcessingException;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.app.comment.Comment;
import pl.coderslab.app.comment.CommentService;
import pl.coderslab.app.user.UserNotFoundException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/picture")
public class PictureController {

    private final PictureService pictureService;
    private final CommentService commentService;
    private final Map<String, String> ALLOWED_PICUTRES_TYPE_BY_TWO_FIRST_BYTES = Map.of(
            "JPEG", "ffd8", "PNG", "8950", "BMP", "424d", "TIFF", "4949"
    );
    private final String PUBLIC_FLAG = "publicFlag";

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("picture", new Picture());
        return "add_picture";
    }

    @PostMapping("/add")
    public String add(@RequestParam("file") MultipartFile file, @RequestParam int publicFlag, Model model, Principal principal) {

            String fileName = file.getOriginalFilename();
            try {
                byte[] bytePic = file.getBytes();
                if (!pictureValidate(bytePic[0], bytePic[1])) {
                    throw new NotCorrectFileUploadException();
                }
                pictureService.save(fileName, bytePic, principal, publicFlag);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                e.getStackTrace();
                e.getMessage();
                model.addAttribute("error", "You must select the image to upload");
                return "add_picture";

            } catch (NotCorrectFileUploadException e) {
                e.printStackTrace();
                model.addAttribute("error", ALLOWED_PICUTRES_TYPE_BY_TWO_FIRST_BYTES.keySet());
                return "add_picture";
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        return "redirect:add";

    }


    @GetMapping(value = "/read/{id}")
    public String readPicture(@PathVariable Long id, Model model) {
        Picture picture = pictureService.findById(id);
        model.addAttribute("singlePicture", picture);
        model.addAttribute("comment", new Comment());
        model.addAttribute("commentsList", commentService.readAllByPictureId(id));
        return "display_single_picture";
    }
    @PostMapping(value = "/{id}/add_comment")
    public String addComment(@PathVariable Long id, @ModelAttribute @Valid Comment comment, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "redirect:../read/" + id;
        }
        commentService.save(comment, id, principal.getName());
        return "redirect:../read/" + id;
    }

    @RequestMapping(value = "/read/all/public/page/{page}")
    public String readAllPublicPictures(Model model, @PathVariable("page") int page) {

        List<Picture> encodedPictures = pictureService.findAllPublicPaginable(page);
        model.addAttribute("pages", pictureService.totalPagesNoPublic(page));
        model.addAttribute("pictures", encodedPictures);
        model.addAttribute("currentPage", page);
        return "display_picture";
    }

    @RequestMapping(value = "/my_gallery/page/{page}")
    public String readMyGallery(Model model, @PathVariable("page") int page, Principal principal) {

        List<Picture> encodedPictures = pictureService.findAllPictureByUserLogin(page, principal.getName());
        model.addAttribute("pages", pictureService.totalPagesNoUserGallery(page,principal.getName()));
        model.addAttribute("pictures", encodedPictures);
        model.addAttribute("currentPage", page);
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

    @GetMapping("/{id}/details")
    public String pictureDetails(@PathVariable Long id, Model model) throws ImageProcessingException, IOException {
        model.addAttribute("details", pictureService.getExifInfo(id));
        return "picture_details";
    }


    private boolean pictureValidate(byte first, byte second) {

        if (ALLOWED_PICUTRES_TYPE_BY_TWO_FIRST_BYTES.containsValue(Integer.toHexString(first & 0xFF)+Integer.toHexString(second & 0xFF))) {
            return true;
        }
        else return false;
    }
}
