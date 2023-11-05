package bg.softuni.ShopApp.init;

import bg.softuni.ShopApp.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleService roleService;

    public DatabaseInitializer(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        roleService.initRoles();
    }
}
