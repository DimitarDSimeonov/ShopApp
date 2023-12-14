package bg.softuni.shop_app.service.impl;

import bg.softuni.shop_app.model.entity.Role;
import bg.softuni.shop_app.model.entity.User;
import bg.softuni.shop_app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Optional;

import static bg.softuni.shop_app.model.entity.enums.RoleName.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class MyUserDetailsServiceTest {

    private MyUserDetailsService myUserDetailsServiceToTet;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        myUserDetailsServiceToTet = new MyUserDetailsService(mockUserRepository);
    }

    @Test
    void loadUserByUsername() {
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

        when(mockUserRepository.findByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        UserDetails userDetails = myUserDetailsServiceToTet.loadUserByUsername(user.getUsername());

        assertNotNull(userDetails);

        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
        assertEquals(user.getRoles().size(), userDetails.getAuthorities().size());
    }
}