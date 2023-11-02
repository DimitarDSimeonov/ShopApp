package bg.softuni.ShopApp.repository;

import bg.softuni.ShopApp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    boolean existByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    boolean existByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber")
    boolean existByPhoneNumber(String phoneNumber);
}
