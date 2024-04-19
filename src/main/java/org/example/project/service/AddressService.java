package org.example.project.service;

import org.example.project.model.Account;
import org.example.project.model.Address;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface AddressService {
    Address addAddress(Address address);
    void deleteAddressById(Long id);
    Address updateAddress(Address address);
    List<Address> getAllAddresses();
    Address getAddressById(Long id);
}