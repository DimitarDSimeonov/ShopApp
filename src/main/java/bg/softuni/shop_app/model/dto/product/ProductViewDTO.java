package bg.softuni.shop_app.model.dto.product;

import bg.softuni.shop_app.model.entity.Picture;
import bg.softuni.shop_app.model.entity.User;
import bg.softuni.shop_app.model.entity.enums.Category;
import bg.softuni.shop_app.model.entity.enums.Location;

import java.math.BigDecimal;
import java.util.List;

public class ProductViewDTO {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Category category;
    private Location location;
    private List<Picture> pictures;
    private User seller;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
