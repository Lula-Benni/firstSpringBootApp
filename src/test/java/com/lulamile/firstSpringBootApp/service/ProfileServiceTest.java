package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Address;
import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.repository.ContactRepository;
import com.lulamile.firstSpringBootApp.repository.ProfileRepository;
import com.lulamile.firstSpringBootApp.utils.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProfileServiceTest {
    @Autowired
    private ProfileService profileService;
    @MockBean
    private ProfileRepository profileRepository;

    @BeforeEach
    void setUp() {
        Contact contact = Contact.builder()
                .cellNumber("0768264985")
                .emails("lulabenni45@gmail.com")
                .contactId(1)
                .build();
        Address address = Address.builder()
                .state("Western Cape")
                .city("Cape Town")
                .street("Hodi rd")
                .country("South Africa")
                .postalCode("7750")
                .addressId(1)
                .build();

        Profile profile = Profile.builder()
                .dateOfBirth(new Date(1999 - 3 - 29))
                .password("12345678")
                .profileId(1)
                .gender(Gender.MALE)
                .contact(contact)
                .address(address)
                .fullName("Lulamile Plati")
                .userName("lula99")
                .passwordResetToken("1@3rfvtr56821445")
                .build();
        Mockito.when(profileRepository.findByEmailsIgnoreCase("lulabenni45@gmail.com"))
                .thenReturn(Optional.ofNullable(profile));
    }

    @Test
    void fetchProfileByUserName() {
        String userName = "lula99";
        Optional<Profile> found = profileService.fetchProfileByUserName(userName);
        if (found.isPresent()) {
            Profile profile = found.get();
            assertEquals(userName, profile.getUserName());
        } else {
            System.out.println("Profile with username: " + userName + " does not exist");
        }
    }
    @Test
    void fetchProfileByEmail() {
        String email = "lulabenni45@gmail.com";
        Optional<Profile> found = profileService.fetchProfileByEmail(email);
        if (found.isPresent()) {
            Profile profile = found.get();
            assertEquals(email, profile.getContact().getEmails());
        }
        else{
            System.out.println("Profile with email: "+email+" does not exist");
        }
    }
}