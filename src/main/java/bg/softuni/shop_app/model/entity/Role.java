package bg.softuni.shop_app.model.entity;

import bg.softuni.shop_app.model.entity.enums.RoleName;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends  BaseEntity{

    @Enumerated(EnumType.STRING)
    private RoleName name;

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return name == role.name;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
