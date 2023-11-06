package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.model.entity.Role;
import bg.softuni.ShopApp.model.entity.User;
import bg.softuni.ShopApp.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .map(MyUserDetailsService::mapUserDetail)
                .orElseThrow(() -> new UsernameNotFoundException("Неправилно потребителско име или парола"));
    }

    private static UserDetails mapUserDetail(User user) {
        return
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .authorities(user.getRoles().stream().map(MyUserDetailsService::mapRoles).collect(Collectors.toList()))
                        .build();
    }

    private static GrantedAuthority mapRoles(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.getName().name());
    }
}
