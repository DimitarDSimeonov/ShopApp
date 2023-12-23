package bg.softuni.shop_app.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @OneToOne(mappedBy = "user")
    private OrderHistory orderHistory;

    @OneToOne(mappedBy = "user")
    private Order order;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToOne(mappedBy = "user")
    private Picture picture;

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    private List<Product> offerProduct;

    public User() {
        roles = new ArrayList<>();
    }
}
