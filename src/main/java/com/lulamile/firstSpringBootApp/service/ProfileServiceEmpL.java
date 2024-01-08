package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Address;
import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


@Service
public class ProfileServiceEmpL implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ContactService contactService;
    @Override
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public List<Profile> fetchProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public Profile fetchProfile(int profileId) {
        return profileRepository.findById(profileId).get();
    }

    @Override
    public String deleteProfile(int profileId) {
        profileRepository.deleteById(profileId);
        return "Profile with Id "+profileId+" is deleted successFully";
    }

    @Override
    public Profile updateProfile(int profileId, Profile profile) {
        Predicate<Object> validate =(input)-> Objects.nonNull(input) && !input.toString().isEmpty();
        Profile profileDB = profileRepository.findById(profileId).get();
        if(validate.test( profile.getFullName())){
            profileDB.setFullName(profile.getFullName());
        }
        if(validate.test(profile.getContact())){
            profileDB.setContact(profile.getContact());
        }
        if(validate.test(profile.getPassword())){
            profileDB.setPassword(profile.getPassword());
        }
        if(validate.test(profile.getUserName()) ){
            profileDB.setUserName(profile.getUserName());
        }
        if(validate.test(profile.getAddress())){
            profileDB.setAddress(profile.getAddress());
        }
        if(validate.test(profile.getDateOfBirth())){
            profileDB.setDateOfBirth(profile.getDateOfBirth());
        }
        if(validate.test(profile.getGender())){
            profileDB.setGender(profile.getGender());
        }
        if (validate.test(profile.getItems())){
            profileDB.setItems(profile.getItems());
        }
        return profileRepository.save(profileDB);
    }

    @Override
    public Profile fetchProfileByName(String fullName) {
        return profileRepository.findByFullNameIgnoreCase(fullName);
    }
}
