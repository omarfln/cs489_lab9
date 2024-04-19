package org.example.project.service.impl;

import org.example.project.dto.UserDTO;
import org.example.project.model.Address;
import org.example.project.model.User;
import org.example.project.repository.AddressRepository;
import org.example.project.repository.UserRepository;
import org.example.project.service.AddressService;
import org.example.project.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository){
        this.userRepository = userRepository;
        this.addressRepository =addressRepository;
    }
    @Override
    public UserDTO addUser(User user) {
        Address address =user.getAddress();
        addressRepository.save(address);
        return convertToDTO(userRepository.save(user));
    }

    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO updateUser(User user) {
        return convertToDTO(userRepository.save(user));
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setAddress(user.getAddress());
        return dto;
    }
    //@Override
    //public List<User> getAllUsers() {
     //   return userRepository.findAll();
    //}

    @Override
    public UserDTO getUserById(Long id) {
        return convertToDTO(userRepository.getReferenceById(id));
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsernameOrFirstNameOrLastNameContainingIgnoreCaseSingle(username);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
    @Override
    public UserDetails loadUserByUsername2(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user)
        );


    }
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        // Assuming you have a getRoles() method that returns a Set<Role>
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }


    @Override
    public List<UserDTO> searchUser(String keyWord) {
        return userRepository.findByUsernameOrFirstNameOrLastNameContainingIgnoreCase(keyWord).stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
