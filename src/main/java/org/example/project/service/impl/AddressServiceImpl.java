package org.example.project.service.impl;

import org.example.project.model.Address;
import org.example.project.repository.AddressRepository;
import org.example.project.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    public  AddressServiceImpl (AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }
    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.getReferenceById(id);
    }
}
