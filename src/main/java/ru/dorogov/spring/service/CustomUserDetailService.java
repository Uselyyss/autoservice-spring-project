package ru.dorogov.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dorogov.spring.models.User;
import ru.dorogov.spring.DAO.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByLogin(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        String role = "ROLE_USER";
        authorities.add(new SimpleGrantedAuthority(role));
        return new CustomUserDetails(user.getId().intValue(), username, user.getPassword(), authorities);
    }
}