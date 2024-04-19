package org.example.project.controller;

import org.example.project.model.Address;
import org.example.project.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mbweb/api/v1/address")
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Address>> listAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<Address> registerAddress(@RequestBody Address address) {
        return ResponseEntity.ok(addressService.addAddress(address));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        address.setId(id);
        return ResponseEntity.ok(addressService.updateAddress(address));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddressById(id);
        return ResponseEntity.ok().build();
    }
}