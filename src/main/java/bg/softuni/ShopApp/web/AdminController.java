package bg.softuni.ShopApp.web;

import bg.softuni.ShopApp.service.CommentService;
import bg.softuni.ShopApp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CommentService commentService;
    private final ProductService productService;

    public AdminController(CommentService commentService, ProductService productService) {
        this.commentService = commentService;
        this.productService = productService;
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
}
