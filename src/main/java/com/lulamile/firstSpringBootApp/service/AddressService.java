package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Address;

import java.util.List;

public interface AddressService {
    Address saveAddress(Address address);

    List<Address> fetchAddresses();

    Address fetchAddressById(int addressId);

    String deleteAddress(int addressId);

    Address updateAddress(int addressId, Address address);
}
