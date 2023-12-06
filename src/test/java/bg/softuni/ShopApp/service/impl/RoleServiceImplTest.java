package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.model.entity.Role;
import bg.softuni.ShopApp.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static bg.softuni.ShopApp.model.entity.enums.RoleName.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    private RoleServiceImpl roleServiceToTest;

    @Mock
    private RoleRepository mockRoleRepository;

    @BeforeEach
    void setUp() {
        roleServiceToTest = new RoleServiceImpl(mockRoleRepository);
    }

    @Test
    void getByName() {
        Role roleUser = new Role();
        roleUser.setName(USER);

        when(mockRoleRepository.findByName(USER))
                .thenReturn(roleUser);

        assertEquals(USER, roleServiceToTest.getByName(USER).getName());
    }

    @Test
    void initRoles() {
        when(mockRoleRepository.count())
                .thenReturn(0L);

        roleServiceToTest.initRoles();

        verify(mockRoleRepository, times(2)).save(any(Role.class));
    }
}