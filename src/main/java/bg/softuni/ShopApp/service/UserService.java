package bg.softuni.ShopApp.service;

public interface UserService {

    boolean existUsername(String username);

    boolean existEmail(String email);

    boolean existPhoneNumber(String phoneNumber);
}
