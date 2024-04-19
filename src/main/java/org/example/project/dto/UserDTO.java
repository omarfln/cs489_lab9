package org.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.project.model.Address;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private  Long id;
    private  String username;
    private  String firstName;
    private  String lastName;
    private  Address address;
}
