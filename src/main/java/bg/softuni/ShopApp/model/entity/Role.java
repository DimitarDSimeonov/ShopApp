package bg.softuni.ShopApp.model.entity;

import bg.softuni.ShopApp.model.entity.eums.RoleName;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends  BaseEntity{

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public Role() {
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
