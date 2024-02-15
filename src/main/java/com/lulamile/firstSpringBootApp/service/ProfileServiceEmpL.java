package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.repository.ContactRepository;
import com.lulamile.firstSpringBootApp.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;


@Service
public class ProfileServiceEmpL implements ProfileService, UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Profile saveProfile(Profile profile) {
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
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
        if(validate.test(profile.getPassword())){
            profileDB.setPassword(profile.getPassword());
        }
        if(validate.test(profile.getUserName()) ){
            profileDB.setUserName(profile.getUserName());
        }
        if(validate.test(profile.getContact())){
            profileDB.setContact(profile.getContact());
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
        if(validate.test(profile.getPasswordResetToken())){
            profileDB.setPasswordResetToken(profile.getPasswordResetToken());
        }
        if (validate.test(profile.getPassword_reset_token_expDate())){
            profileDB.setPassword_reset_token_expDate(profile.getPassword_reset_token_expDate());
        }
        return profileRepository.save(profileDB);
    }

    @Override
    public Profile fetchProfileByUserName(String userName) {
        return profileRepository.findByUserNameIgnoreCase(userName);
    }

    @Override
    public Optional<Profile> fetchProfileByEmail(String emails) {
        Optional<Profile> optionalProfile = profileRepository.findByEmailsIgnoreCase(emails);
        System.out.println("**************"+optionalProfile.isEmpty()+"**************");

        if(optionalProfile.isPresent()){
            return optionalProfile;
        }
        else{
            System.out.println("Profile not found for email: "+emails);
            throw new EntityNotFoundException("Profile not found for email: "+emails);
        }
        //return profileRepository.findByEmailsIgnoreCase(emails);
        /*Optional<Contact> contact = contactRepository.findContactByEmailsIgnoreCase(emails);
        if(contact.isPresent()){
            return profileRepository.findByContactEmailsIgnoreCase(emails);
        }
        else{
            System.out.println("Contact not found for email: "+emails);
            throw new EntityNotFoundException("Contact not found for email: "+emails);
        }*/
    }

    @Override
    public Optional<Profile> fetchProfileByToken(String token) {
        return profileRepository.findByPasswordResetToken(token);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Profile> optionalProfile = profileRepository.findOneByUserNameIgnoreCase(userName);
        if(optionalProfile.isEmpty()){
            throw new UsernameNotFoundException("Profile with username "+userName+" is not found");
        }
        Profile profile = optionalProfile.get();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("Allow"));

        return new User(profile.getUserName(),profile.getPassword(),grantedAuthorities);
    }
}
