package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.model.DTO.user.UserRegisterDTO;
import bg.softuni.ShopApp.model.entity.User;
import bg.softuni.ShopApp.model.entity.enums.RoleName;
import bg.softuni.ShopApp.repository.UserRepository;
import bg.softuni.ShopApp.service.RoleService;
import bg.softuni.ShopApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean existUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElse(null) != null;
    }

    @Override
    public boolean existEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElse(null) != null;
    }

    @Override
    public boolean existPhoneNumber(String phoneNumber) {
        return userRepository
                .findByPhoneNumber(phoneNumber)
                .orElse(null) != null;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {

        userRegisterDTO.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        User user = modelMapper.map(userRegisterDTO, User.class);
        user.setRoles(roleService.getByName(RoleName.USER));
        userRepository.save(user);
    }
}
