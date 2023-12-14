package bg.softuni.shop_app.web;

import bg.softuni.shop_app.service.CommentService;
import bg.softuni.shop_app.service.ProductService;
import bg.softuni.shop_app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CommentService commentService;
    private final ProductService productService;
    private final UserService userService;

    public AdminController(CommentService commentService, ProductService productService, UserService userService) {
        this.commentService = commentService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/comments")
    public String allComments(Model model) {

        model.addAttribute("comments", commentService.getAllComments());

        return "admin-comments";
    }

    @DeleteMapping("/comment/delete/{id}")
    public String deleteCommentById(@PathVariable("id") Long id) {

        commentService.deleteById(id);

        return "redirect:/admin/comments";
    }

    @GetMapping("/products")
    public String allProducts(Model model) {

        model.addAttribute("products", productService.getAllProducts());

        return "admin-product";
    }

    @DeleteMapping("/product/delete/{id}")
    public String deleteProductById(@PathVariable("id") Long id) {

        productService.deleteById(id);

        return "redirect:/admin/products";
    }

    @GetMapping("/users")
    public String allUsers(Model model, Principal principal)  {

        model.addAttribute("users", userService.getAllUsers(principal.getName()));

        return "admin-users";
    }

    @PutMapping("/user/add-role/{id}")
    public String addAdminRole(@PathVariable("id") Long id) {

        userService.addAdminRole(id);

        return "redirect:/admin/users";
    }

    @PutMapping("/user/remove-role/{id}")
    public String removeAdminRole(@PathVariable("id") Long id) {

        userService.removeAdminRole(id);

        return "redirect:/admin/users";
    }
}
