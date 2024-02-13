package com.lulamile.firstSpringBootApp.controller;

import com.lulamile.firstSpringBootApp.entity.Address;
import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.service.AddressService;
import com.lulamile.firstSpringBootApp.service.ContactService;
import com.lulamile.firstSpringBootApp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/profile/username/{username}")
    public Profile fetchProfileByUserName(@PathVariable("username") String userName){
        return profileService.fetchProfileByUserName(userName);
    }
    @GetMapping("/by-email")
    public ResponseEntity<Optional<Profile>> getProfileByEmail(@RequestParam String emails) {
        Optional<Profile> profile = profileService.fetchProfileByEmail(emails);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
