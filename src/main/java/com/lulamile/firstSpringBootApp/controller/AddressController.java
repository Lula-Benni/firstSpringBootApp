package com.lulamile.firstSpringBootApp.controller;

import com.lulamile.firstSpringBootApp.entity.Address;
import com.lulamile.firstSpringBootApp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;
    @PostMapping(value = "/address", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Address saveAddress(@RequestBody Address address){return addressService.saveAddress(address);}
    @GetMapping("/address")
    public List<Address> fetchAddresses(){return addressService.fetchAddresses();}
    @GetMapping("/address/{id}")
    public Address fetchAddressById(@PathVariable("id") int addressId){return addressService.fetchAddressById(addressId);}
    @DeleteMapping("/address/{id}")
    public String deleteAddress(@PathVariable("id") int addressId){return addressService.deleteAddress(addressId);}
    @PutMapping("/address/{id}")
    public Address updateAddress(@PathVariable("id") int addressId,@RequestBody Address address){return addressService.updateAddress(addressId,address);}
}
