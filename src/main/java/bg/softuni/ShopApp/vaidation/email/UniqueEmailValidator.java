package bg.softuni.ShopApp.vaidation.email;

import bg.softuni.ShopApp.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;

    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueEmail email) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userService.existEmail(email);
    }
}
