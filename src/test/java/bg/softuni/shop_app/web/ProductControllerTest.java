package bg.softuni.shop_app.web;

import bg.softuni.shop_app.model.dto.comment.CommentAddDTO;
import bg.softuni.shop_app.model.dto.product.AddProductDTO;
import bg.softuni.shop_app.service.CommentService;
import bg.softuni.shop_app.service.PictureService;
import bg.softuni.shop_app.service.PictureUploadService;
import bg.softuni.shop_app.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    private ProductController productControllerToTest;

    @Mock
    private ProductService mockProductService;

    @Mock
    private PictureUploadService mockPictureUploadService;

    @Mock
    private PictureService mockPictureService;

    @Mock
    private CommentService mockCommentService;

    private static final String MODEL_KEY_FOR_ADD_PRODUCT_DTO = "addProductDTO";

    @BeforeEach
    void setUp() {
        productControllerToTest = new ProductController(mockProductService,
                mockPictureUploadService, mockPictureService, mockCommentService);
    }

    @Test
    void addProduct() {
        Model model = mock(Model.class);

        when(model.containsAttribute(MODEL_KEY_FOR_ADD_PRODUCT_DTO))
                .thenReturn(true);
        assertEquals("product-add", productControllerToTest.addProduct(model));

        when(model.containsAttribute(MODEL_KEY_FOR_ADD_PRODUCT_DTO))
                .thenReturn(false);
        assertEquals("product-add", productControllerToTest.addProduct(model));
    }

    @Test
    void addProductConfirm() {
        AddProductDTO addProductDTO = new AddProductDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
        Principal principal = mock(Principal.class);

        when(bindingResult.hasErrors())
                .thenReturn(true);
        assertEquals("redirect:add", productControllerToTest.addProductConfirm(addProductDTO,
                bindingResult,
                redirectAttributes,
                principal));

        when(bindingResult.hasErrors())
                .thenReturn(false);
        when(principal.getName())
                .thenReturn("username");

        String username = principal.getName();
        when(mockProductService.createProduct(addProductDTO, username))
                .thenReturn(2L);

        Long id = mockProductService.createProduct(addProductDTO, username);

        assertEquals("redirect:/products/add/owner/view/" + id, productControllerToTest.addProductConfirm(addProductDTO,
                bindingResult,
                redirectAttributes,
                principal));
    }

    @Test
    void deleteById() {
        Long id = 2L;

        assertEquals("redirect:/home", productControllerToTest.deleteById(id));
        verify(mockProductService).deleteById(id);
    }

    @Test
    void viewAfterCreate() {
        Long id = 2L;
        Model model = mock(Model.class);

        assertEquals("product-owner-view", productControllerToTest.viewAfterCreate(id, model));
    }

    @Test
    void addPicture() throws IOException {
        Long id = 2L;
        MultipartFile multipartFile = mock(MultipartFile.class);

        when(mockPictureUploadService.uploadFile(multipartFile))
                .thenReturn("url");

        String url = mockPictureUploadService.uploadFile(multipartFile);

        assertEquals("redirect:/products/add/owner/view/" + id, productControllerToTest.addPicture(id, multipartFile));
        verify(mockPictureService).setProduct(id, url);
    }

    @Test
    void productDetails() {
        Long id = 2L;
        Model model = mock(Model.class);

        assertEquals("product-details-view", productControllerToTest.productDetails(id, model));
        verify(mockProductService).getDetailsViewById(id);
    }

    @Test
    void addCommentConfirm() {
        Long id = 2L;
        CommentAddDTO commentAddDTO = new CommentAddDTO();
        Principal principal = mock(Principal.class);

        assertEquals("redirect:/products/view/" + id, productControllerToTest.addCommentConfirm(id, commentAddDTO, principal));
        verify(mockCommentService).createComment(commentAddDTO, id, principal.getName());
    }
}