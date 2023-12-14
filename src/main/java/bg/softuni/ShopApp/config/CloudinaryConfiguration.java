package bg.softuni.ShopApp.config;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CloudinaryConfiguration {

    private static final String CLOUD_NAME = "dhkodgkzy";
    private static final String API_KEY = "144849744445361";
    private static final String API_SECRET = System.getenv("CLOUDINARY_SECRET");

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap("cloud_name", CLOUD_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRET,
                "secure", true));
    }
}
