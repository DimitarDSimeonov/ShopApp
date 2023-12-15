package bg.softuni.shop_app;

import bg.softuni.shop_app.web.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ShopAppApplicationTests {

	@Autowired
	private HomeController homeController;

	@Test
	void contextLoads() {
		assertNotNull(homeController);
	}

}
