package bg.softuni.ShopApp.repository;

import bg.softuni.ShopApp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber")
    Optional<User> findByPhoneNumber(String phoneNumber);

    List<User> findAllByUsernameNot(String username);
}
