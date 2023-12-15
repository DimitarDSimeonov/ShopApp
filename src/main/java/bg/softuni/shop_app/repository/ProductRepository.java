package bg.softuni.shop_app.repository;

import bg.softuni.shop_app.model.entity.Product;
import bg.softuni.shop_app.model.entity.enums.Category;
import bg.softuni.shop_app.model.entity.enums.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<List<Product>> findAllByDateOfPostBefore(LocalDateTime localDateTime);

    List<Product> findByTitleContainingIgnoreCaseAndPriceLessThanEqualAndLocationAndCategory(String title, BigDecimal price, Location location, Category category);

    @Query("SELECT p FROM Product p ORDER BY p.dateOfPost DESC LIMIT 10")
    List<Product> findTop10OrderByDateOfPostDesc();
}