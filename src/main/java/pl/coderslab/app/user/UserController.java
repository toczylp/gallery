package pl.coderslab.app.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;

@Transactional
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userServiceImpl;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    @PostMapping("/signup")
    public String signup(@ModelAttribute @Valid User user, BindingResult result) {
        if(result.hasErrors()) {
            return "signup";
        }

        userServiceImpl.saveUser(user);
        return  "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
    @GetMapping("/perform_logout")
    public String logoutGet() {
        return "logout";
    }

    @PostMapping("/loggedin")
    public String loggedIn(@ModelAttribute User user) {
        return "loggedin";
    }

    @GetMapping("/user/details")
    public String userDetails(Model model, Principal principal) {
        try {
            User user = userServiceImpl.findByLogin(principal.getName());
            model.addAttribute("user", user);
        } catch (UserNotFoundException e) {
            e.getMessage();
        }
        return "user_details";
    }
    @PostMapping("/user/details")
    public String userUpdate(@ModelAttribute @Valid User user) {

        try {
            userServiceImpl.updateUser(user);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }


        return "user_details";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@ModelAttribute User user) {
        userServiceImpl.deleteByUserId(user.getId());

        return "redirect:../../perform_logout";
    }

    @GetMapping("/user/deleteByAdmin/{id}")
    public String deleteByAdmin(@PathVariable Long id) {
        userServiceImpl.deleteByUserId(id);
        return "redirect:../../user/read/all";
    }

    @GetMapping("/user/read/all")
    public String readAllUsers(Model model) {
        model.addAttribute("users", userServiceImpl.findAll());
        return "users";
    }
}
