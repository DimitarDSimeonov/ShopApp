package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.repository.UserRepository;
import bg.softuni.ShopApp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean existUsername(String username) {
        return userRepository.existByUsername(username);
    }

    @Override
    public boolean existEmail(String email) {
        return userRepository.existByEmail(email);
    }

    @Override
    public boolean existPhoneNumber(String phoneNumber) {
        return userRepository.existByPhoneNumber(phoneNumber);
    }
}
