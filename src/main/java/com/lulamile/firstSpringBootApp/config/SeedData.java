package com.lulamile.firstSpringBootApp.config;

import com.lulamile.firstSpringBootApp.entity.Address;
import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.service.AddressService;
import com.lulamile.firstSpringBootApp.service.ContactService;
import com.lulamile.firstSpringBootApp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private AddressService addressService;
    @Override
    public void run(String... args) throws Exception {


        /*List<Profile> profiles = profileService.fetchProfiles();
        if(profiles.isEmpty()){
            Contact contact01 = new Contact();
            Address address01 = new Address();

            contact01.setCellNumber("0768264985");
            contact01.setEmails("ubenni45@gmail.com");
            contactService.saveContact(contact01);

            address01.setState("Western Cape");
            address01.setCountry("South Africa");
            address01.setCity("Cape Town");
            address01.setStreet("Liesbeek 9");
            address01.setPostalCode("7700");
            address01.setAdditionalDetails("Kendal 9");
            addressService.saveAddress(address01);

            Profile profile01 = new Profile();
            profile01.setFullName("lula");
            profile01.setUserName("lulamile");
            profile01.setPassword("profile2");
            profile01.setContact(contact01);
            profile01.setAddress(address01);
            profile01.setDateOfBirth(new Date(1999-12-20));

            profileService.saveProfile(profile01);
        }*/
    }
}
