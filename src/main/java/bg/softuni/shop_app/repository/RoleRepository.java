package bg.softuni.shop_app.repository;

import bg.softuni.shop_app.model.entity.Role;
import bg.softuni.shop_app.model.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleName roleName);
}
