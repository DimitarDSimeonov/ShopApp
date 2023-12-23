package bg.softuni.shop_app.model.entity;

import bg.softuni.shop_app.model.entity.enums.RoleName;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "roles")
public class Role extends  BaseEntity{

    @Enumerated(EnumType.STRING)
    private RoleName name;

}
