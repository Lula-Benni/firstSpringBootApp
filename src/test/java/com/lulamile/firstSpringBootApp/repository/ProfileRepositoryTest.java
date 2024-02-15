package com.lulamile.firstSpringBootApp.repository;

import com.lulamile.firstSpringBootApp.entity.Address;
import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.utils.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ProfileRepositoryTest {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private TestEntityManager entityManager;
    @BeforeEach
    void setUp() {
        Contact contact = Contact.builder()
                .cellNumber("0768264985")
                .emails("lulabenni45@gmail.com")
                .build();
        entityManager.persist(contact);
        Address address = Address.builder()
                .state("Western Cape")
                .city("Cape Town")
                .street("Hodi rd")
                .country("South Africa")
                .postalCode("7750")
                .build();
        entityManager.persist(address);
        Profile profile = Profile.builder()
                .dateOfBirth(new Date(1999- 3 -29))
                .password("12345678")
                .gender(Gender.MALE)
                .contact(contact)
                .address(address)
                .fullName("Lulamile Plati")
                .userName("lula99")
                .passwordResetToken("1@3rfvtr56821445")
                .passwordResetToken("12dfdghnnyhkmrknogj94")
                .build();
        entityManager.persist(profile);
    }

    @Test
    void findOneByUserNameIgnoreCase() {
        String userName = "lula99";
        Optional<Profile> found = profileRepository.findOneByUserNameIgnoreCase(userName);
        if(found.isPresent()){
            Profile profile = found.get();
            assertEquals(userName,profile.getUserName());
        }
        else{
            System.out.println("Profile with username: "+userName+" does not exist");
        }
    }

    @Test
    void findByEmailsIgnoreCase() {
        String email = "lulabenni45@gmail.com";
        Optional<Profile> found = profileRepository.findByEmailsIgnoreCase(email);
        if(found.isPresent()){
            Profile profile = found.get();
            assertEquals(email,profile.getContact().getEmails());
        }
        else{
            System.out.println("Profile with email: "+email+" does not exist");
        }
    }

    @Test
    void findByPasswordResetToken() {
        String token = "12dfdghnnyhkmrknogj94";
        Optional<Profile> found = profileRepository.findByPasswordResetToken(token);
        if(found.isPresent()){
            Profile profile = found.get();
            assertEquals(token,profile.getPasswordResetToken());
        }
        else{
            System.out.println("Token does not exist");
        }
    }
}