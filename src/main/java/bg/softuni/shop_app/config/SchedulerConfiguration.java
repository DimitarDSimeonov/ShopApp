package bg.softuni.shop_app.config;

import bg.softuni.shop_app.service.ProductService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfiguration {

    private final ProductService productService;

    public SchedulerConfiguration(ProductService productService) {
        this.productService = productService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void clearOldProduct() {
        productService.clearOldProduct();
    }
}
