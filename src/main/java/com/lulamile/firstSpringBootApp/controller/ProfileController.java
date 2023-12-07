package com.lulamile.firstSpringBootApp.controller;

import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.service.ProfileServiceEmpL;
import com.lulamile.firstSpringBootApp.service.ProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    @Autowired
    private ProfileServices profileServices;
@PostMapping("/profile")
    public Profile saveProfile(@RequestBody Profile profile){
        return  profileServices.saveProfile(profile);
    }
}
