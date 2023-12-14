package bg.softuni.shop_app.web;

import bg.softuni.shop_app.model.dto.comment.CommentAddDTO;
import bg.softuni.shop_app.model.dto.product.AddProductDTO;
import bg.softuni.shop_app.model.dto.product.ProductViewDTO;
import bg.softuni.shop_app.service.CommentService;
import bg.softuni.shop_app.service.PictureService;
import bg.softuni.shop_app.service.PictureUploadService;
import bg.softuni.shop_app.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final PictureUploadService pictureUploadService;
    private final PictureService pictureService;
    private final CommentService commentService;
    private static final String MODEL_KEY_FOR_ADD_PRODUCT_DTO = "addProductDTO";

    public ProductController(ProductService productService, PictureUploadService pictureUploadService, PictureService pictureService, CommentService commentService) {
        this.productService = productService;
        this.pictureUploadService = pictureUploadService;
        this.pictureService = pictureService;
        this.commentService = commentService;
    }

    @GetMapping("/add")
    public String addProduct(Model model) {

        if (!model.containsAttribute(MODEL_KEY_FOR_ADD_PRODUCT_DTO)) {
            model.addAttribute(MODEL_KEY_FOR_ADD_PRODUCT_DTO, new AddProductDTO());
        }

        return "product-add";
    }

    @PostMapping("/add")
    public String addProductConfirm(@Valid AddProductDTO addProductDTO,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(MODEL_KEY_FOR_ADD_PRODUCT_DTO, addProductDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProductDTO", bindingResult);
            return "redirect:add";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();


        Long id = productService.createProduct(addProductDTO, username);

        return "redirect:/products/add/owner/view/" + id;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {

        productService.deleteById(id);

        return "redirect:/home";
    }

    @GetMapping("/add/owner/view/{id}")
    public String viewAfterCreate(@PathVariable Long id, Model model) {

        model.addAttribute("view", productService.getOwnerViewById(id));

        return "product-owner-view";
    }

    @PostMapping("/add/owner/view/{id}")
    public String addPicture(@PathVariable Long id,
                             @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String url = pictureUploadService.uploadFile(multipartFile);

        pictureService.setProduct(id, url);

        return "redirect:/products/add/owner/view/" + id;
    }

    @GetMapping("/view/{id}")
    public String productDetails(@PathVariable Long id, Model model) {

        ProductViewDTO productViewDTO = productService.getDetailsViewById(id);

        model.addAttribute("product", productViewDTO);
        model.addAttribute("commentAddDTO", new CommentAddDTO());
        return "product-details-view";
    }

    @PostMapping("/view/{id}")
    public String addCommentConfirm(@PathVariable("id") Long id, CommentAddDTO commentAddDTO, Principal principal) {

        commentService.createComment(commentAddDTO, id, principal.getName());

        return "redirect:/products/view/" + id;
    }
}
