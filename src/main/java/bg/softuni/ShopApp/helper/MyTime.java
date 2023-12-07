package bg.softuni.ShopApp.helper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
@Configuration
public class MyTime {
    @Bean
    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}
