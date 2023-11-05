package bg.softuni.ShopApp.service;

import bg.softuni.ShopApp.model.DTO.user.UserRegisterDTO;

public interface UserService {

    boolean existUsername(String username);

    boolean existEmail(String email);

    boolean existPhoneNumber(String phoneNumber);

    void register(UserRegisterDTO userRegisterDTO);
}
