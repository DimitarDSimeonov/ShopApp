package bg.softuni.shop_app.vaidation.password;

import bg.softuni.shop_app.model.dto.user.UserRegisterDTO;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class PasswordMatcherValidatorTest {

    private PasswordMatcherValidator passwordMatcherValidatorToTest;

    @BeforeEach
    void setUp() {
        passwordMatcherValidatorToTest = new PasswordMatcherValidator();
    }

    @Test
    void isValid() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setPassword("123");
        userRegisterDTO.setConfirmPassword("123");

        assertTrue(passwordMatcherValidatorToTest.isValid(userRegisterDTO, mock(ConstraintValidatorContext.class)));
    }
}