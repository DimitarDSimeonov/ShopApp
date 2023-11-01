package bg.softuni.ShopApp.repository;

import bg.softuni.ShopApp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existByUsername(String username);

    boolean existByEmail(String email);
}
