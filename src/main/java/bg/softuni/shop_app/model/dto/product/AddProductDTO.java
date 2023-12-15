package bg.softuni.shop_app.model.dto.product;

import bg.softuni.shop_app.model.entity.enums.Category;
import bg.softuni.shop_app.model.entity.enums.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPicturesURL(String picturesURL) {
        this.pictureURL = picturesURL;
    }
}
