package bg.softuni.shop_app.model.dto.product;

import bg.softuni.shop_app.model.entity.enums.Category;
import bg.softuni.shop_app.model.entity.enums.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AddProductDTO {

    @NotBlank(message = "Моля въведете заглавие!")
    private String title;

    @NotBlank(message = "Моля въведете описание!")
    private String description;

    @NotNull(message = "Моля Въведете цена!")
    @Positive(message = "Цената не може да бъде отрицателна!")
    private BigDecimal price;
    private Category category;
    private Location location;
    private String pictureURL;

}
