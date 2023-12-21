package bg.softuni.shop_app.web;

import bg.softuni.shop_app.model.dto.product.ProductSearchDTO;
import bg.softuni.shop_app.model.dto.product.ProductViewDTO;
import bg.softuni.shop_app.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchControllerTest {

    private SearchController searchControllerToTest;

    @Mock
    private ProductService mockProductService;

    @BeforeEach
    void setUp() {
        searchControllerToTest = new SearchController(mockProductService);
    }

    @Test
    void search() {
        Model model = mock(Model.class);

        when(model.containsAttribute("productSearchDTO"))
                .thenReturn(true);
        assertEquals("product-search", searchControllerToTest.search(model));

        when(model.containsAttribute("productSearchDTO"))
                .thenReturn(false);
        assertEquals("product-search", searchControllerToTest.search(model));
    }

    @Test
    void searchConfirm() {
        ProductSearchDTO productSearchDTO = new ProductSearchDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
        Model model = mock(Model.class);
        List<ProductViewDTO> products = new ArrayList<>();

        when(bindingResult.hasErrors())
                .thenReturn(true);

        assertEquals("redirect:search", searchControllerToTest.searchConfirm(productSearchDTO,
                bindingResult,
                redirectAttributes,
                model));

        when(bindingResult.hasErrors())
                .thenReturn(false);

        when(mockProductService.searchByInput(productSearchDTO))
                .thenReturn(products);

        assertEquals("product-search-result", searchControllerToTest.searchConfirm(productSearchDTO,
                bindingResult,
                redirectAttributes,
                model));
    }
}