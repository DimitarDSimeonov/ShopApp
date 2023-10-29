package bg.softuni.ShopApp.model.entity;

import bg.softuni.ShopApp.model.entity.eums.Category;
import bg.softuni.ShopApp.model.entity.eums.Location;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "date_of_post")
    private LocalDateTime dateOfPost;

    @Enumerated(EnumType.STRING)
    private Location location;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;


    public Product() {
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

    public LocalDateTime getDateOfPost() {
        return dateOfPost;
    }

    public void setDateOfPost(LocalDateTime dateOfPost) {
        this.dateOfPost = dateOfPost;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
