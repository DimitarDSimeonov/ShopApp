package bg.softuni.shop_app.service;

import bg.softuni.shop_app.model.entity.Role;
import bg.softuni.shop_app.model.entity.enums.RoleName;

public interface RoleService {
    Role getByName(RoleName roleName);

    void initRoles();
}
