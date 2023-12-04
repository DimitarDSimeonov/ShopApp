package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.model.entity.Role;
import bg.softuni.ShopApp.model.entity.enums.RoleName;
import bg.softuni.ShopApp.repository.RoleRepository;
import bg.softuni.ShopApp.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public void initRoles() {
        if (roleRepository.count() == 0) {
            Arrays.stream(RoleName.values())
                    .forEach(roleName -> {
                        Role role = new Role();
                        role.setName(roleName);
                        roleRepository.save(role);
                    });
        }
    }
}
