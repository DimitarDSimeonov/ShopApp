package bg.softuni.shop_app.model.dto.product;

import bg.softuni.shop_app.model.entity.Picture;
import bg.softuni.shop_app.model.entity.User;
import bg.softuni.shop_app.model.entity.enums.Category;
import bg.softuni.shop_app.model.entity.enums.Location;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductViewDTO {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Category category;
    private Location location;
    private List<Picture> pictures;
    private User seller;
}
