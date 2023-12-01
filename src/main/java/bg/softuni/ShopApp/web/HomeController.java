package bg.softuni.ShopApp.web;

import bg.softuni.ShopApp.service.ProductService;
import bg.softuni.ShopApp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String home(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        model.addAttribute("myOffers", userService.getMyOffers(username));

        return "home";
    }
}
