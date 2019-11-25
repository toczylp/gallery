package pl.coderslab.app.picture;

import com.drew.imaging.ImageProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.app.category.Category;
import pl.coderslab.app.category.CategoryService;
import pl.coderslab.app.comment.Comment;
import pl.coderslab.app.comment.CommentService;
import pl.coderslab.app.user.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
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
    private final CategoryService categoryService;
    private final Map<String, String> ALLOWED_PICUTRES_TYPE_BY_TWO_FIRST_BYTES = Map.of(
            "JPEG", "ffd8", "PNG", "8950", "BMP", "424d", "TIFF", "4949"
    );
    private final String PUBLIC_FLAG = "publicFlag";

    @GetMapping("/add")
    public String addPictureForm(Model model) {
        model.addAttribute("picture", new Picture());
        return "add_picture";
    }

    @PostMapping("/add")
    public String addPicture(@RequestParam("file") MultipartFile file, @RequestParam int publicFlag, @RequestParam String category, Model model, Principal principal) {

        String fileName = file.getOriginalFilename();
        Category categoryDb = categoryService.readCategoryByName(category);

        if (categoryDb == null) {
            model.addAttribute("noCategoryChosenError", "You must choose the category");
            return "add_picture";
        }

        try {
            byte[] bytePic = file.getBytes();
            if (!pictureValidate(bytePic[0], bytePic[1])) {
                throw new NotCorrectFileUploadException();
            }
            pictureService.save(fileName, bytePic, principal, publicFlag, categoryDb);
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
    public String readPicture(@PathVariable Long id, Model model, HttpServletRequest request) throws ImageProcessingException, IOException {

        Picture picture = pictureService.findByIdAndIncreaseViewsQty(id);
        request.getParameter("excessiveCommentLengthValidationError");

        if(request.getParameter("alreadyRankedFlag") != null) {
            model.addAttribute("alreadyRankedFlag", request.getParameter("alreadyRankedFlag"));
        }
        if(request.getParameter("excessiveCommentLengthValidationError") != null) {
            model.addAttribute("error", request.getParameter("excessiveCommentLengthValidationError"));
        }

        model.addAttribute("singlePicture", picture);
        model.addAttribute("comment", new Comment());
        model.addAttribute("commentsList", commentService.readAllByPictureId(id));
        model.addAttribute("details", pictureService.getExifInfo(id));
        return "display_single_picture";
    }
    @PostMapping(value = "/{id}/add_comment")
    public String addComment(@PathVariable Long id, @ModelAttribute @Valid Comment comment, BindingResult result, Principal principal, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("excessiveCommentLengthValidationError", result.getFieldError("content").getDefaultMessage());
            pictureService.decreaseViewsQtybyOne(id);
            return "redirect:../read/" + id;
        }
        comment.setId(null); //zapytać się o nullowanie obiektu
        pictureService.decreaseViewsQtybyOne(id);
        commentService.save(comment, id, principal.getName());
        return "redirect:../read/" + id;
    }

    @PostMapping(value = "/{id}/rate")
    public String rate(@PathVariable Long id, Model model, HttpServletRequest request, Principal principal) throws UserNotFoundException {

        String rate = request.getParameter("rating");
        int rateAsInt = 0;
        if(rate != null) {
            rateAsInt = Integer.parseInt(rate);
        }
        boolean alreadyRankedFlag = pictureService.rate(rateAsInt, id, principal.getName());
        if(!alreadyRankedFlag) {
            model.addAttribute("alreadyRankedFlag", !alreadyRankedFlag);
        }
        pictureService.decreaseViewsQtybyOne(id);
        return "redirect:../read/" + id;
    }

    @RequestMapping(value = "/all/public/page/{page}")
    public String readAllPublicPictures(Model model, @PathVariable("page") int page) {

        List<Picture> encodedPictures = pictureService.findAllPublicPaginable(page);
        model.addAttribute("paginationFlag", "2");
        model.addAttribute("pages", pictureService.totalPagesNoPublic(page));
        model.addAttribute("pictures", encodedPictures);
        model.addAttribute("currentPage", page);
        return "display_picture";
    }

    @RequestMapping(value = "/read/most_viewed/page/{page}")
    public String readAllPublicMostViewedPictures(Model model, @PathVariable("page") int page) {

        List<Picture> encodedPictures = pictureService.findAllPaginableOrderedByDirectViewsQty(page);
        model.addAttribute("paginationFlag", "4");
        model.addAttribute("pages", pictureService.totalPagesNoPublic(page));
        model.addAttribute("pictures", encodedPictures);
        model.addAttribute("currentPage", page);
        return "display_picture";
    }

    @RequestMapping(value = "/read/top_rated/page/{page}")
    public String readAllPublicTopRatedPictures(Model model, @PathVariable("page") int page) {

        List<Picture> encodedPictures = pictureService.findAllPaginableOrderedByRating(page);
        model.addAttribute("paginationFlag", "6");
        model.addAttribute("pages", pictureService.totalPagesNoPublic(page));
        model.addAttribute("pictures", encodedPictures);
        model.addAttribute("currentPage", page);
        return "display_picture";
    }

    @RequestMapping(value = "/my_gallery/page/{page}")
    public String readMyGallery(Model model, @PathVariable("page") int page, Principal principal) {
        model.addAttribute("paginationFlag", "3");
        List<Picture> encodedPictures = pictureService.findAllPictureByUserLogin(page, principal.getName());
        model.addAttribute("pages", pictureService.totalPagesNoUserGallery(page,principal.getName()));
        model.addAttribute("pictures", encodedPictures);
        model.addAttribute("currentPage", page);
        return "display_picture";
    }

    @GetMapping(value = "/read/latest")
    public String readLatest(Model model) {
        model.addAttribute("pictures", pictureService.findLatestSixPublicPictures());
        return "display_picture";
    }

    @RequestMapping(value = "/read/all/page/{page}")
    public String readAllPictures(Model model, @PathVariable("page") int page) {

        List<Picture> encodedPictures = pictureService.findAllPaginable(page);
        model.addAttribute("pages", pictureService.totalPagesNo(page));
        model.addAttribute("pictures", encodedPictures);
        model.addAttribute("currentPage", page);
        model.addAttribute("paginationFlag", "1");
        return "display_picture";
    }

    @RequestMapping(value = "read/category/{category}/page/{page}")
    public String readAllPicturesByCategory(@PathVariable int page, @PathVariable String category, Model model) {
        List<Picture> encodedPictures = pictureService.findAllPublicByCategoryPaginable(page, category);
        model.addAttribute("pages", pictureService.totalPagesNoPublicOfGivenCategory(page, category));
        model.addAttribute("pictures", encodedPictures);
        model.addAttribute("currentPage", page);
        model.addAttribute("category", category);
        model.addAttribute("paginationFlag", "5");
        return "display_picture";
    }

    @GetMapping(value = "/delete/{id}")
    public String delet(@PathVariable Long id) {
        pictureService.deletePicture(id);
        return "redirect:../my_gallery/page/1";
    }

    private boolean pictureValidate(byte first, byte second) {

        if (ALLOWED_PICUTRES_TYPE_BY_TWO_FIRST_BYTES.containsValue(Integer.toHexString(first & 0xFF)+Integer.toHexString(second & 0xFF))) {
            return true;
        }
        else return false;
    }

    @ModelAttribute(name = "categories")
    public List<Category> categories() {
        return categoryService.readAllCategories();
    }
}
