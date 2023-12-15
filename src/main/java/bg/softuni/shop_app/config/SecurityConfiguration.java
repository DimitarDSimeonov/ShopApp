package bg.softuni.shop_app.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
        authorizeRequest ->
               authorizeRequest
               .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
               .requestMatchers("/", "/users/register", "/users/login", "users/login-error", "/products/view/**", "comments/view/**").permitAll()
               .requestMatchers("/admin/**").hasRole("ADMIN")
               .anyRequest().authenticated()
        ).formLogin(
               formLogin ->
                     formLogin
                          .loginPage("/users/login")
                          .usernameParameter("username")
                          .passwordParameter("password")
                          .defaultSuccessUrl("/", true)
                          .failureForwardUrl("/users/login-error")
        ).logout(
               logout ->
                     logout
                          .logoutUrl("/users/logout")
                          .logoutSuccessUrl("/")
                          .invalidateHttpSession(true)
        ).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
