package bg.softuni.ShopApp.web;

import bg.softuni.ShopApp.model.DTO.product.AddProductDTO;
import bg.softuni.ShopApp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addProduct(Model model) {

        if (!model.containsAttribute("addProductDTO")) {
            model.addAttribute("addProductDTO", new AddProductDTO());
        }

        AddProductDTO addProductDTO = (AddProductDTO) model.getAttribute("addProductDTO");
        addProductDTO.getPicturesURL().add((String) model.getAttribute("url"));

        model.addAttribute("addProductDTO", addProductDTO);
        return "product-add";
    }

    @PostMapping("/add")
    public String addProductConfirm(@Valid AddProductDTO addProductDTO,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addProductDTO", addProductDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProductDTO", bindingResult);
            return "redirect:add";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        productService.createProduct(addProductDTO, username);

        return "redirect:/home";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {

        productService.removeById(id);

        return "redirect:/home";
    }
}
