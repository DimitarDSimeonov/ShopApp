package bg.softuni.shop_app.model.dto.product;

import java.math.BigDecimal;

public class ProductHomePageViewDTO {

    private Long id;
    private String title;
    private BigDecimal price;

    public ProductHomePageViewDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
