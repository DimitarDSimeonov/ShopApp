package bg.softuni.shop_app.model.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductHomePageViewDTO {

    private Long id;
    private String title;
    private BigDecimal price;
}
