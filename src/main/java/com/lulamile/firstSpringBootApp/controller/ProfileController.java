package com.lulamile.firstSpringBootApp.controller;

import com.lulamile.firstSpringBootApp.entity.Address;
import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.service.AddressService;
import com.lulamile.firstSpringBootApp.service.ContactService;
import com.lulamile.firstSpringBootApp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ContactService contactService;
    @PostMapping("/profile")
    public Profile saveProfile(@RequestBody Profile profile){
        return  profileService.saveProfile(profile);
    }
    @GetMapping("/profile")
    public List<Profile> fetchProfiles(){
        return profileService.fetchProfiles();
    }
    @GetMapping("/profile/{id}")
    public Profile fetchProfile(@PathVariable("id") int profileId){
        return profileService.fetchProfile(profileId);
    }
    @DeleteMapping("/profile/{id}")
    public String deleteProfile(@PathVariable("id") int profileId){
        return profileService.deleteProfile(profileId);
    }
    @PutMapping("/profile/{id}")
    public Profile updateProfile(@PathVariable("id") int profileId, @RequestBody Profile profile){
        return profileService.updateProfile(profileId, profile);
    }
    @GetMapping("/profile/name/{name}")
    public Profile fetchProfileByName(@PathVariable("name") String fullName){
        return profileService.fetchProfileByName(fullName);
    }
}
