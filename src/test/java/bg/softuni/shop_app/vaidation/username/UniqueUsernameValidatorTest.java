package bg.softuni.shop_app.vaidation.username;

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
class UniqueUsernameValidatorTest {

    private UniqueUsernameValidator uniqueUsernameValidatorToTest;

    @Mock
    private UserService mockUserService;

    @BeforeEach
    void setUp() {
        uniqueUsernameValidatorToTest = new UniqueUsernameValidator(mockUserService);
    }

    @Test
    void isValid() {
        when(mockUserService.existUsername("exist username"))
                .thenReturn(true);

        when(mockUserService.existUsername("not exist username"))
                .thenReturn(false);

        assertTrue(uniqueUsernameValidatorToTest.isValid("not exist username", mock(ConstraintValidatorContext.class)));
        assertFalse(uniqueUsernameValidatorToTest.isValid("exist username", mock(ConstraintValidatorContext.class)));
    }
}