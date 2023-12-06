package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.model.DTO.product.ProductHomePageViewDTO;
import bg.softuni.ShopApp.model.DTO.user.UserRegisterDTO;
import bg.softuni.ShopApp.model.DTO.user.UserViewDTO;
import bg.softuni.ShopApp.model.entity.Product;
import bg.softuni.ShopApp.model.entity.Role;
import bg.softuni.ShopApp.model.entity.User;
import bg.softuni.ShopApp.repository.UserRepository;
import bg.softuni.ShopApp.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static bg.softuni.ShopApp.model.entity.enums.RoleName.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserServiceImpl userServiceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private RoleService mockRoleService;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    void setUp() {
        userServiceToTest = new UserServiceImpl(mockUserRepository,
                mockModelMapper, mockRoleService, mockPasswordEncoder);
    }

    @Test
    void existUsername() {
        User testUser = createUser();

        when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        boolean exist = userServiceToTest.existUsername(testUser.getUsername());

        assertTrue(exist);

        exist = userServiceToTest.existUsername("ala-bala");

        assertFalse(exist);
    }

    @Test
    void existEmail() {
        User testUser = createUser();

        when(mockUserRepository.findByEmail(testUser.getEmail()))
                .thenReturn(Optional.of(testUser));

        boolean exist = userServiceToTest.existEmail(testUser.getEmail());

        assertTrue(exist);

        exist = userServiceToTest.existEmail("ala-bala");

        assertFalse(exist);
    }

    @Test
    void existPhoneNumber() {
        User testUser = createUser();

        when(mockUserRepository.findByPhoneNumber(testUser.getPhoneNumber()))
                .thenReturn(Optional.of(testUser));

        boolean exist = userServiceToTest.existPhoneNumber(testUser.getPhoneNumber());

        assertTrue(exist);

        exist = userServiceToTest.existPhoneNumber("ala-bala");

        assertFalse(exist);
    }

    @Test
    void register() {
        Role roleUser = new Role();
        roleUser.setName(USER);
        when(mockRoleService.getByName(USER))
                .thenReturn(roleUser);

        User testUser = createUser();

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUsername("mitko");
        userRegisterDTO.setFirstName("Dimitar");
        userRegisterDTO.setLastName("Simeonov");
        userRegisterDTO.setPassword("123");
        userRegisterDTO.setPhoneNumber("0999999999");
        userRegisterDTO.setEmail("Mitko@user.bg");

        when(mockUserRepository.save(any(User.class)))
                .thenReturn(testUser);

        when(mockPasswordEncoder.encode(testUser.getPassword()))
                .thenReturn("123");

        when(mockModelMapper.map(userRegisterDTO, User.class ))
                .thenReturn(testUser);

        assertEquals(0, mockUserRepository.count());

        userServiceToTest.register(userRegisterDTO);

        verify(mockUserRepository).save(any(User.class));
    }

    @Test
    void getByUsername() {
        User testUser = createUser();
        when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        User user = userServiceToTest.getByUsername(testUser.getUsername());

        assertNotNull(user);
        assertEquals(user.getUsername(), testUser.getUsername());
        assertEquals(user.getPhoneNumber(), testUser.getPhoneNumber());
        assertEquals(user.getEmail(), testUser.getEmail());
        assertEquals(user.getFirstName(), testUser.getFirstName());
        assertEquals(user.getLastName(), testUser.getLastName());
    }

    @Test
    void getMyOffers() {
        User testUser = createUser();

        when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));


        assertEquals(0, userServiceToTest.getMyOffers(testUser.getUsername()).size());

        testUser.getOfferProduct().add(new Product());

        assertEquals(1,userServiceToTest.getMyOffers(testUser.getUsername()).size());
    }

    @Test
    void getAllUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            User test = createUser();
            test.setUsername(test.getUsername() + i);
            users.add(test);
        }

        User testUser = createUser();

        when(mockUserRepository.findAllByUsernameNot(testUser.getUsername()))
                .thenReturn(users);

        List<UserViewDTO> list = userServiceToTest.getAllUsers(testUser.getUsername());

        assertEquals(3, list.size());
    }

    @Test
    void addAdminRole() {
        User testUser = createUser();
        Role admin = new Role();
        admin.setName(ADMIN);

        when(mockUserRepository.findById(testUser.getId()))
                .thenReturn(Optional.of(testUser));

        when(mockRoleService.getByName(ADMIN))
                .thenReturn(admin);

        when(mockUserRepository.saveAndFlush(any(User.class)))
                .thenReturn(testUser);

        assertEquals(1, testUser.getRoles().size());

        userServiceToTest.addAdminRole(testUser.getId());

        assertEquals(2, testUser.getRoles().size());
    }

    @Test
    void removeAdminRole() {
        User testUser = createAdmin();
        Role admin = new Role();
        admin.setName(ADMIN);

        when(mockUserRepository.findById(testUser.getId()))
                .thenReturn(Optional.of(testUser));

        when(mockRoleService.getByName(ADMIN))
                .thenReturn(admin);

        when(mockUserRepository.saveAndFlush(any(User.class)))
                .thenReturn(testUser);

        assertEquals(2, testUser.getRoles().size());

        userServiceToTest.removeAdminRole(testUser.getId());

        assertEquals(1, testUser.getRoles().size());
    }

    private static User createUser() {
        Role userRole = new Role();
        userRole.setName(USER);
        User user = new User();
        user.setId(1L);
        user.setUsername("mitko");
        user.setFirstName("Dimitar");
        user.setLastName("Simeonov");
        user.setPassword("123");
        user.setPhoneNumber("0999999999");
        user.setEmail("Mitko@user.bg");
        user.setRoles(new ArrayList<>());
        user.setOfferProduct(new ArrayList<>());
        user.getRoles().add(userRole);
        return user;
    }

    private static User createAdmin() {
        Role adminRole = new Role();
        adminRole.setName(ADMIN);
        User user = createUser();
        user.getRoles().add(adminRole);
        return user;
    }
}