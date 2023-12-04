package bg.softuni.ShopApp.repository;

import bg.softuni.ShopApp.model.entity.Role;
import bg.softuni.ShopApp.model.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleName roleName);
}
