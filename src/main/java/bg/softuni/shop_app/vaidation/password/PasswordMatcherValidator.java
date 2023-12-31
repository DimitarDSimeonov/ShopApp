package bg.softuni.shop_app.vaidation.password;

import bg.softuni.shop_app.model.dto.user.UserRegisterDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatcherValidator implements ConstraintValidator<PasswordMatcher, UserRegisterDTO> {

    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext context) {
        return userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword());
    }
}
