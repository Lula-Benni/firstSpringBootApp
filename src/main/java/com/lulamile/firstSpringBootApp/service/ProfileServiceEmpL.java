package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceEmpL implements ProfileServices{
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }
}
