package bg.softuni.shop_app.web;

import bg.softuni.shop_app.service.ProductService;
import bg.softuni.shop_app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final UserService userService;
    private final ProductService productService;

    public HomeController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("lastOffers", productService.getLastProducts());
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {

        String username = principal.getName();

        model.addAttribute("myOffers", userService.getMyOffers(username));

        return "home";
    }
}
