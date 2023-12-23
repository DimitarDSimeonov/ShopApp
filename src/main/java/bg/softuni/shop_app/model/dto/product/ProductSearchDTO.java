package bg.softuni.shop_app.model.dto.product;

import bg.softuni.shop_app.model.entity.enums.Category;
import bg.softuni.shop_app.model.entity.enums.Location;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductSearchDTO {

    private String title;
    @DecimalMin(value = "0.1", message = "Цената не може да бъде отрицателна!")
    private BigDecimal maxPrice;
    private Location location;
    private Category category;
}
