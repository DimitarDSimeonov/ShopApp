package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.model.DTO.product.ProductHomePageViewDTO;
import bg.softuni.ShopApp.model.DTO.user.UserRegisterDTO;
import bg.softuni.ShopApp.model.DTO.user.UserViewDTO;
import bg.softuni.ShopApp.model.entity.User;
import bg.softuni.ShopApp.model.entity.enums.RoleName;
import bg.softuni.ShopApp.repository.UserRepository;
import bg.softuni.ShopApp.service.RoleService;
import bg.softuni.ShopApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        user.getRoles().add(roleService.getByName(RoleName.USER));
        userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public List<ProductHomePageViewDTO> getMyOffers(String username) {
        return userRepository
                .findByUsername(username)
                .get()
                .getOfferProduct()
                .stream()
                .map(product ->
                     modelMapper.map(product, ProductHomePageViewDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<UserViewDTO> getAllUsers(String username) {
        return userRepository.findAllByUsernameNot(username)
                .stream()
                .map(user -> modelMapper.map(user, UserViewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addAdminRole(Long id) {
        User user = userRepository.findById(id).get();

        user.getRoles().add(roleService.getByName(RoleName.ADMIN));

        userRepository.saveAndFlush(user);
    }

    @Override
    public void removeAdminRole(Long id) {
        User user = userRepository.findById(id).get();

        user.getRoles().remove(roleService.getByName(RoleName.ADMIN));

        userRepository.saveAndFlush(user);
    }
}
