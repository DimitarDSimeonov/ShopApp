package bg.softuni.shop_app.web;

import bg.softuni.shop_app.service.CommentService;
import bg.softuni.shop_app.service.ProductService;
import bg.softuni.shop_app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.Model;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    private AdminController adminControllerToTest;

    @Mock
    private CommentService mockCommentService;
    @Mock
    private ProductService mockProductService;
    @Mock
    private UserService mockUserService;

    @BeforeEach
    void setUp() {
        adminControllerToTest = new AdminController(mockCommentService, mockProductService, mockUserService);
    }


    @Test
    @WithMockUser(username = "username", roles={"ADMIN"})
    void allComments() {
        Model model = mock(Model.class);

        assertEquals("admin-comments", adminControllerToTest.allComments(model));
    }

    @Test
    @WithMockUser(username = "username", roles={"ADMIN"})
    void deleteCommentById() {
        Long id = 2L;
        String result = adminControllerToTest.deleteCommentById(id);
        verify(mockCommentService, times(1)).deleteById(id);
        assertEquals("redirect:/admin/comments", result);
    }

    @Test
    @WithMockUser(username = "username", roles={"ADMIN"})
    void allProducts() {
        Model model = mock(Model.class);

        assertEquals("admin-product", adminControllerToTest.allProducts(model));
    }

    @Test
    @WithMockUser(username = "username", roles={"ADMIN"})
    void deleteProductById() {
        Long id = 2L;
        String result = adminControllerToTest.deleteProductById(id);
        verify(mockProductService, times(1)).deleteById(id);
        assertEquals("redirect:/admin/products", result);
    }

    @Test
    @WithMockUser(username = "username", roles={"ADMIN"})
    void allUsers() {
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);

        assertEquals("admin-users", adminControllerToTest.allUsers(model, principal));
    }

    @Test
    @WithMockUser(username = "username", roles={"ADMIN"})
    void addAdminRole() {
        Long id = 2L;
        String result = adminControllerToTest.addAdminRole(id);
        verify(mockUserService, times(1)).addAdminRole(id);
        assertEquals("redirect:/admin/users", result);
    }

    @Test
    @WithMockUser(username = "username", roles={"ADMIN"})
    void removeAdminRole() {
        Long id = 2L;
        String result = adminControllerToTest.removeAdminRole(id);
        verify(mockUserService, times(1)).removeAdminRole(id);
        assertEquals("redirect:/admin/users", result);
    }
}