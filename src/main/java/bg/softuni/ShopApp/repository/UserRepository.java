package bg.softuni.ShopApp.repository;

import bg.softuni.ShopApp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber")
    User findByPhoneNumber(String phoneNumber);
}
