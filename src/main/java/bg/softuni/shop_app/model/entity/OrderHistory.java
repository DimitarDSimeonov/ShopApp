package bg.softuni.shop_app.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "order_history")
public class OrderHistory extends BaseEntity{

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "orderHistory")
    private List<Order> orders;

}
