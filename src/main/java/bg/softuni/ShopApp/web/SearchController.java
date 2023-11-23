package bg.softuni.ShopApp.web;

import bg.softuni.ShopApp.model.DTO.product.ProductSearchDTO;
import bg.softuni.ShopApp.model.DTO.product.ProductViewDTO;
import bg.softuni.ShopApp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SearchController {

    private ProductService productService;

    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public String search(Model model) {
        if (!model.containsAttribute("productSearchDTO")) {
            model.addAttribute("productSearchDTO", new ProductSearchDTO());
        }

        return "product-search";
    }

    @PostMapping("/search")
    public String searchConfirm(@Valid ProductSearchDTO productSearchDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Model model) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productSearchDTO", productSearchDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productSearchDTO", bindingResult);
            return "redirect:search";
        }

        List<ProductViewDTO> products = productService.searchByInput(productSearchDTO);

        model.addAttribute("products", products);

        return "product-search-result";
    }
}
//ToDo: Create searching in title case insensitive!