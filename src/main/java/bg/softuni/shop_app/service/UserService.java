package bg.softuni.shop_app.service;

import bg.softuni.shop_app.model.DTO.product.ProductHomePageViewDTO;
import bg.softuni.shop_app.model.DTO.user.UserRegisterDTO;
import bg.softuni.shop_app.model.DTO.user.UserViewDTO;
import bg.softuni.shop_app.model.entity.User;

import java.util.List;

public interface UserService {

    boolean existUsername(String username);

    boolean existEmail(String email);

    boolean existPhoneNumber(String phoneNumber);

    void register(UserRegisterDTO userRegisterDTO);

    User getByUsername(String username);

    List<ProductHomePageViewDTO> getMyOffers(String username);

    List<UserViewDTO> getAllUsers(String username);

    void addAdminRole(Long id);

    void removeAdminRole(Long id);
}
