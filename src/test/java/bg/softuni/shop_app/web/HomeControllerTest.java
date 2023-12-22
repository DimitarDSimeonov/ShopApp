package bg.softuni.shop_app.web;

import bg.softuni.shop_app.model.dto.product.ProductHomePageViewDTO;
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
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    private HomeController homeControllerToTest;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @BeforeEach
    void setUp() {
        homeControllerToTest = new HomeController(userService, productService);
    }

    @Test
    void index(){

         assertEquals("index", homeControllerToTest.index(mock(Model.class)));
    }

    @Test
    @WithMockUser(username = "username" ,roles = {"ADMIN", "USER"})
    void home() {
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);

        when(principal.getName())
                .thenReturn("username");

        String username = principal.getName();
        assertEquals("home", homeControllerToTest.home(model, principal));
        verify(userService).getMyOffers(username);
    }
}