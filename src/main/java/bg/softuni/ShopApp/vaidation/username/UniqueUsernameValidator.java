package bg.softuni.ShopApp.vaidation.username;

import bg.softuni.ShopApp.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserService userService;

    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void initialize(UniqueUsername username) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !userService.existUsername(username);
    }
}
