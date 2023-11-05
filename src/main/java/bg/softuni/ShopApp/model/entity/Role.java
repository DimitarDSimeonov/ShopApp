package bg.softuni.ShopApp.model.entity;

import bg.softuni.ShopApp.model.entity.eums.RoleName;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends  BaseEntity{

    @Enumerated(EnumType.STRING)
    private RoleName name;

    public Role() {
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
