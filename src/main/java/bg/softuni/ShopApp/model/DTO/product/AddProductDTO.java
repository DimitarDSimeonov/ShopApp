package bg.softuni.ShopApp.model.DTO.product;

import bg.softuni.ShopApp.model.entity.enums.Category;
import bg.softuni.ShopApp.model.entity.enums.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    private List<String> picturesURL;

    public AddProductDTO() {
        picturesURL = new ArrayList<>();
    }

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

    public List<String> getPicturesURL() {
        return picturesURL;
    }

    public void setPicturesURL(List<String> picturesURL) {
        this.picturesURL = picturesURL;
    }
}
