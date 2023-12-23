package bg.softuni.shop_app.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    @OneToOne
    private User user;

    @OneToMany()
    private List<Product> products;

    private LocalDateTime orderTime;

    @ManyToOne()
    private OrderHistory orderHistory;
}
