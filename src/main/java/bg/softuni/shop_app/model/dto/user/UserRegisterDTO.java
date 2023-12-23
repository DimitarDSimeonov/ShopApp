package bg.softuni.shop_app.model.dto.user;

import bg.softuni.shop_app.vaidation.email.UniqueEmail;
import bg.softuni.shop_app.vaidation.password.PasswordMatcher;
import bg.softuni.shop_app.vaidation.phone_number.UniquePhoneNumber;
import bg.softuni.shop_app.vaidation.username.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordMatcher
public class UserRegisterDTO {

    @NotBlank(message = "Моля да въведете потребителско име!")
    @UniqueUsername
    private String username;

    @NotBlank(message = "Моля да въведете парола!")
    private String password;

    @NotBlank(message = "Моля да потвърдите паролата!")
    private String confirmPassword;

    @NotBlank(message = "Моля да въведете име!")
    private String firstName;

    @NotBlank(message = "Моля да въведете фамилия!")
    private String lastName;

    @Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$", message = "Моля въведете валиден имейл!")
    @UniqueEmail
    private String email;

    @Pattern(regexp = "^08[789]{2}\\d{6}$" , message = "Моля въведете валиден телефонен номер!")
    @UniquePhoneNumber
    private String phoneNumber;
}
