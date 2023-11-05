package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.model.DTO.user.UserRegisterDTO;
import bg.softuni.ShopApp.model.entity.User;
import bg.softuni.ShopApp.model.entity.eums.RoleName;
import bg.softuni.ShopApp.repository.UserRepository;
import bg.softuni.ShopApp.service.RoleService;
import bg.softuni.ShopApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public boolean existUsername(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public boolean existEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public boolean existPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber) != null;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {

        User user = modelMapper.map(userRegisterDTO, User.class);
        user.setRoles(roleService.getByName(RoleName.USER));
        userRepository.save(user);
    }
}
