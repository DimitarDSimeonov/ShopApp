package bg.softuni.shop_app.web;

import bg.softuni.shop_app.model.DTO.user.UserRegisterDTO;
import bg.softuni.shop_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private static final String MODEL_KEY_FOR_USER_REGISTER_DTO = "userRegisterDTO";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/login-error")
    public String loginError(@ModelAttribute("username") String username, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("username", username);
        redirectAttributes.addFlashAttribute("error", true);

        return "redirect:login";
    }

    @GetMapping("/register")
    public String register(Model model) {

        if (!model.containsAttribute(MODEL_KEY_FOR_USER_REGISTER_DTO)) {
            model.addAttribute(MODEL_KEY_FOR_USER_REGISTER_DTO, new UserRegisterDTO());
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterDTO userRegisterDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(MODEL_KEY_FOR_USER_REGISTER_DTO, userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            return "redirect:register";
        }

        userService.register(userRegisterDTO);
        return "redirect:login";
    }
}
