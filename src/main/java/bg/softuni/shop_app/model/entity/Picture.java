package bg.softuni.shop_app.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity{

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    private Product product;

    @OneToOne
    private User user;
}
