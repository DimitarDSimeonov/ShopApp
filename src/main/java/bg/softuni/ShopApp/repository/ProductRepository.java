package bg.softuni.ShopApp.repository;

import bg.softuni.ShopApp.model.entity.Product;
import bg.softuni.ShopApp.model.entity.enums.Category;
import bg.softuni.ShopApp.model.entity.enums.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitleContainingAndPriceLessThanAndLocationAndCategory(String title, BigDecimal price, Location location, Category category);
}
//ToDo: Create query with var args...

//@Query("SELECT p FROM Product p WHERE (:title is NULL OR p.title LIKE %:title%) AND " +
//            "(:maxPrice is NULL or :maxPrice <= p.price) AND " +
//            "(:location is NULL or :location = p.location) AND " +
//            "(:category is NULL or :category = p.category) " +
//            "ORDER BY p.dateOfPost DESC") work when given only 1 argument!
//    List<Product> findBySearchingInput(@Param("title")String title,
//                                   @Param("maxPrice")BigDecimal maxPrice,
//                                   @Param("location")Location location,
//                                   @Param("category")Category category);