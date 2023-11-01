package bg.softuni.ShopApp.vaidation.phoneNumber;

import bg.softuni.ShopApp.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, String> {

    private final UserService userService;

    public UniquePhoneNumberValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniquePhoneNumber phoneNumber) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return !userService.existPhoneNumber(phoneNumber);
    }
}
