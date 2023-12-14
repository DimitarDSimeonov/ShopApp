package bg.softuni.shop_app.service.impl;

import bg.softuni.shop_app.model.entity.Role;
import bg.softuni.shop_app.model.entity.enums.RoleName;
import bg.softuni.shop_app.repository.RoleRepository;
import bg.softuni.shop_app.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
