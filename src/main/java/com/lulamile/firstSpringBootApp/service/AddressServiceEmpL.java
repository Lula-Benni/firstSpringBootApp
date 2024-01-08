package com.lulamile.firstSpringBootApp.service;
import com.lulamile.firstSpringBootApp.entity.Address;
import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.repository.AddressRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
@Service
public class AddressServiceEmpL implements AddressService{
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }
    @Override
    public List<Address> fetchAddresses() {
        return addressRepository.findAll();
    }
    @Override
    public Address fetchAddressById(int addressId) {
        return addressRepository.findById(addressId).get();
    }
    @Override
    public String deleteAddress(int addressId) {
        addressRepository.deleteById(addressId);
        return "Address with Id "+addressId+" is deleted successfully";
    }

    @Override
    public Address updateAddress(int addressId, Address address) {
        Address addressDB = addressRepository.findById(addressId).get();
        Predicate<Object> validate = (input) -> Objects.nonNull(input) && !input.toString().isEmpty();
        if(validate.test(address.getStreet())){
            addressDB.setStreet(address.getStreet());
        }
        if(validate.test(address.getCity())){
            addressDB.setCity(address.getCity());
        }
        if(validate.test(address.getPostalCode())){
            addressDB.setPostalCode(address.getPostalCode());
        }
        if(validate.test(address.getState())){
            addressDB.setState(address.getState());
        }
        if(validate.test(address.getCountry())){
            addressDB.setCountry(address.getCountry());
        }
        if(validate.test(address.getAdditionalDetails())){
            addressDB.setAdditionalDetails(address.getAdditionalDetails());
        }
        return addressRepository.save(addressDB);
    }
}
