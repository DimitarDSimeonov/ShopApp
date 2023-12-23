package bg.softuni.shop_app.model.dto.user;

import bg.softuni.shop_app.model.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserViewDTO {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles;
}
