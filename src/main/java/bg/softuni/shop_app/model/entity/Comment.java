package bg.softuni.shop_app.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter @Setter
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private LocalDateTime date;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User author;

    public String getAuthorFullName() {
        return author.getFirstName() + " " + author.getLastName();
    }
}
