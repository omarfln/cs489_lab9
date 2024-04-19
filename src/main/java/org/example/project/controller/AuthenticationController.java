package org.example.project.controller;

import lombok.AllArgsConstructor;
import org.example.project.model.User;
import org.example.project.security.JwtTokenService;
import org.example.project.service.CustomUserDetailsService;
import org.example.project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import registrationsystem.api.dto.LoginDTO;
import registrationsystem.api.dto.response.LoginResponseDTO;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDetailsService;
    public final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //String username = auth.getName(); // This gets the username
        String username = loginDTO.getUsername();
        User tmp = new User();
        System.out.println("USERRRRRRRRRRRRRR isssssss"+username);

        User user = userService.findByUsername(username);
        String jwt = jwtTokenService.generateToken(user);
        LoginResponseDTO loginResponseDTO = modelMapper.map(user, LoginResponseDTO.class);
        loginResponseDTO.setToken(jwt);
        return ResponseEntity.ok(loginResponseDTO);
    }
}
