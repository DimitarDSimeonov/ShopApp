package bg.softuni.ShopApp.model.DTO.user;

import bg.softuni.ShopApp.vaidation.email.UniqueEmail;
import bg.softuni.ShopApp.vaidation.password.PasswordMatcher;
import bg.softuni.ShopApp.vaidation.phoneNumber.UniquePhoneNumber;
import bg.softuni.ShopApp.vaidation.username.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

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
    @NotBlank(message = "Моля да въведете имай!")
    @UniqueEmail
    private String email;

    @NotBlank(message = "Моля да въведете телефонен номер!")
    @Pattern(regexp = "^0[0-9]{9}$" , message = "Моля въведете валиден телефонен номер!")
    @UniquePhoneNumber
    private String phoneNumber;

    public UserRegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
