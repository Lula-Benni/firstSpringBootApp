package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Address;
import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.repository.ProfileRepository;
import com.lulamile.firstSpringBootApp.utils.Gender;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class ProfileServiceEmpLTest {
    @MockBean
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileServiceEmpL profileServiceEmpL;
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
                .passwordResetToken("13rfvtr56821445")
                .address(address)
                .fullName("Lulamile Plati")
                .userName("lula99")
                .build();
        Mockito.when(profileRepository.findByEmailsIgnoreCase("lulabenni45@gmail.com"))
                .thenReturn(Optional.ofNullable(profile));
        Mockito.when(profileRepository.findOneByUserNameIgnoreCase("lula99"))
                .thenReturn(Optional.ofNullable(profile));
        Mockito.when(profileRepository.findByPasswordResetToken("13rfvtr56821445"))
                .thenReturn(Optional.ofNullable(profile));
    }
    @ParameterizedTest
    @MethodSource("getTestCasesForLoadByUsername")
    void loadUserByUsername(String userName) {
        Optional<Profile> optionalProfile = profileRepository.findOneByUserNameIgnoreCase(userName);
        if (profileRepository.findOneByUserNameIgnoreCase(userName).isPresent()) {
            UserDetails found = profileServiceEmpL.loadUserByUsername(userName);
            assertEquals(userName, found.getUsername());
        } else {
            Exception exception = assertThrows(UsernameNotFoundException.class,()->profileServiceEmpL.loadUserByUsername(userName));
            assertEquals("Profile with that username is not found",exception.getMessage());
        }
    }

    private Stream<String> getTestCasesForLoadByUsername(){
        return Stream.of("lula99", "lu01@lu.com", null, " ","");
    }
}