package bg.softuni.shop_app.web;

import bg.softuni.shop_app.model.dto.user.UserRegisterDTO;
import bg.softuni.shop_app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private UserController userControllerToTest;

    @Mock
    private UserService mockUserService;

    private static final String MODEL_KEY_FOR_USER_REGISTER_DTO = "userRegisterDTO";

    @BeforeEach
    void setUp() {
        userControllerToTest = new UserController(mockUserService);
    }

    @Test
    void login() {
        assertEquals("login", userControllerToTest.login());
    }

    @Test
    void loginError() {
        String username = "username";
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        assertEquals("redirect:login", userControllerToTest.loginError(username,redirectAttributes));
    }

    @Test
    void register() {
        Model model = mock(Model.class);

        when(model.containsAttribute(MODEL_KEY_FOR_USER_REGISTER_DTO))
                .thenReturn(true);

        assertEquals("register", userControllerToTest.register(model));

        when(model.containsAttribute(MODEL_KEY_FOR_USER_REGISTER_DTO))
                .thenReturn(false);

        assertEquals("register", userControllerToTest.register(model));
    }

    @Test
    void registerConfirm() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        when(bindingResult.hasErrors())
                .thenReturn(true);

        assertEquals("redirect:register", userControllerToTest.registerConfirm(userRegisterDTO,
                bindingResult,
                redirectAttributes));

        when(bindingResult.hasErrors())
                .thenReturn(false);

        assertEquals("redirect:login", userControllerToTest.registerConfirm(userRegisterDTO,
                bindingResult,
                redirectAttributes));

        verify(mockUserService, times(1)).register(userRegisterDTO);
    }
}