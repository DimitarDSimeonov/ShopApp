package bg.softuni.ShopApp.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
        authorizeRequest ->
               authorizeRequest
               .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
               .requestMatchers("/", "/users/register", "/users/login").permitAll()
               .requestMatchers("/resources/static/*").permitAll()
               //ToDo make other link
        ).formLogin(
               formLogin ->
                     formLogin
                          .loginPage("/users/login")
                          .usernameParameter("username")
                          .passwordParameter("password")
                          .defaultSuccessUrl("/")
                          .failureForwardUrl("/users/login-error")
        ).logout(
               logout ->
                     logout
                          .logoutUrl("users/logout")
                          .logoutSuccessUrl("/")
                          .invalidateHttpSession(true)
        ).build();
    }
}
