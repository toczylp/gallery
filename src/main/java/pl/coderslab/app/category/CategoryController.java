package pl.coderslab.app.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/read/all")
    public String readAllCategories(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        model.addAttribute("categoryList", categoryService.readAllCategories());
        return "categories";
    }

    @GetMapping("/add")
    public String addCategory(@ModelAttribute @Valid Category category, BindingResult result) {

        if(result.hasErrors()) {
            return "categories";
        }
        categoryService.save(category);
        return "redirect:./read/all";
    }
}
