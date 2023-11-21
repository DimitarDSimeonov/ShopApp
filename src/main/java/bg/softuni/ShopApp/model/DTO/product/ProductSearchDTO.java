package bg.softuni.ShopApp.model.DTO.product;

import bg.softuni.ShopApp.model.entity.enums.Category;
import bg.softuni.ShopApp.model.entity.enums.Location;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public class ProductSearchDTO {

    private String title;
    @DecimalMin(value = "0.1", message = "Цената не може да бъде отрицателна!")
    private BigDecimal maxPrice;
    private Location location;
    private Category category;

    public ProductSearchDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
