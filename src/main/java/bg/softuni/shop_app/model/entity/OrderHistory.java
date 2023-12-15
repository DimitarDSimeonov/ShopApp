package bg.softuni.shop_app.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "order_history")
public class OrderHistory extends BaseEntity{

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "orderHistory")
    private List<Order> orders;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
