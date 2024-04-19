package org.example.project.service;

import lombok.NoArgsConstructor;
import org.example.project.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.project.repository.UserRepository;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    //@Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User domainUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with usernamessssssssss: " + username));

        // Convert to Spring Security UserDetails
        return org.springframework.security.core.userdetails.User.builder()
                .username(domainUser.getUsername())
                .password(domainUser.getPassword())
                .authorities(domainUser.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()))
                .build();
    }

}