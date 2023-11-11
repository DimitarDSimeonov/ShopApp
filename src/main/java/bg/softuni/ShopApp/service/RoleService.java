package bg.softuni.ShopApp.service;

import bg.softuni.ShopApp.model.entity.Role;
import bg.softuni.ShopApp.model.entity.enums.RoleName;

import java.util.List;

public interface RoleService {
    List<Role> getByName(RoleName roleName);

    void initRoles();
}
