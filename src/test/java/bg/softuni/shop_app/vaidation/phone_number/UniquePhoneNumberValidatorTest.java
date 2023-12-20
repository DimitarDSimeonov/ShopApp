package bg.softuni.shop_app.vaidation.phone_number;

import bg.softuni.shop_app.service.UserService;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UniquePhoneNumberValidatorTest {

    private UniquePhoneNumberValidator uniquePhoneNumberValidatorToTest;

    @Mock
    private UserService mockUserService;

    @BeforeEach
    void setUp() {
        uniquePhoneNumberValidatorToTest = new UniquePhoneNumberValidator(mockUserService);
    }

    @Test
    void isValid() {
        when(mockUserService.existPhoneNumber("exist phone number"))
                .thenReturn(true);

        when(mockUserService.existPhoneNumber("not exist phone number"))
                .thenReturn(false);

        assertTrue(uniquePhoneNumberValidatorToTest.isValid("not exist phone number", mock(ConstraintValidatorContext.class)));
        assertFalse(uniquePhoneNumberValidatorToTest.isValid("exist phone number", mock(ConstraintValidatorContext.class)));
    }
}