package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Address;
import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.entity.Profile;

import java.util.List;

public interface ProfileService {
    public Profile saveProfile(Profile profile);
    public List<Profile> fetchProfiles();
    public Profile fetchProfile(int profileId);
    public String deleteProfile(int profileId);
    public Profile updateProfile(int profileId, Profile profile);
    public Profile fetchProfileByName(String fullName);
}
