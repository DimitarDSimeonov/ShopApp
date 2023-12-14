package bg.softuni.shop_app.vaidation.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatcherValidator.class)
public @interface PasswordMatcher {

    String message() default "Въведените пароли не съвпадат!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
