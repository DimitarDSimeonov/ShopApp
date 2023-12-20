package bg.softuni.shop_app.vaidation.email;

import bg.softuni.shop_app.model.dto.user.UserRegisterDTO;
import bg.softuni.shop_app.service.UserService;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UniqueEmailValidatorTest {

    private UniqueEmailValidator uniqueEmailValidatorToTest;

    @Mock
    private UserService mockUserService;

    @BeforeEach
    void setUp() {
        uniqueEmailValidatorToTest = new UniqueEmailValidator(mockUserService);
    }

    @Test
    void isValid() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setEmail("test@mail.com");

        when(mockUserService.existEmail(userRegisterDTO.getEmail()))
                .thenReturn(true);


        assertFalse(uniqueEmailValidatorToTest.isValid(userRegisterDTO.getEmail(), mock(ConstraintValidatorContext.class)));
        assertTrue(uniqueEmailValidatorToTest.isValid("ala bala", mock(ConstraintValidatorContext.class)));
    }
}