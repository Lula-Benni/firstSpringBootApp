package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Address;
import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.repository.ProfileRepository;
import com.lulamile.firstSpringBootApp.utils.Gender;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.action.internal.EntityActionVetoException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
    @MethodSource("getTestCasesForFetchProfileByUserName")
    void fetchProfileByUserName(String userName) {
        if (profileRepository.findOneByUserNameIgnoreCase(userName).isPresent()) {
            Optional<Profile> found = profileService.fetchProfileByUserName(userName);
            Profile profile = found.get();
            assertEquals(userName, profile.getUserName());
        } else {
            Exception exception = assertThrows(EntityNotFoundException.class,()->profileService.fetchProfileByUserName(userName));
            assertEquals("Profile not found for UserName",exception.getMessage());
        }
    }
    private Stream<String> getTestCasesForFetchProfileByUserName(){
        return  Stream.of("lula99", "lu01@lu.com", null, " ","");
    }
    @ParameterizedTest
    @MethodSource("getTestCasesForFetchProfileByEmail")
    void fetchProfileByEmail(String email) {
        if (profileRepository.findByEmailsIgnoreCase(email).isPresent()) {
            Optional<Profile> found = profileService.fetchProfileByEmail(email);
            Profile profile = found.get();
            assertEquals(email, profile.getContact().getEmails());
        }
        else{
            Exception exception =assertThrows(EntityNotFoundException.class,()->profileService.fetchProfileByEmail(email));
            assertEquals("Profile not found for email", exception.getMessage());
        }
    }
    private Stream<String> getTestCasesForFetchProfileByEmail(){
        return  Stream.of("lulabenni45@gmail.com","lu01@lu.com","lula",null," ");
    }
    @Test
    @Disabled
    void fetchProfileByToken() {
        String token = "13rfvtr56821445";
        Optional<Profile> found = profileService.fetchProfileByToken(token);
        if (found.isPresent()) {
            Profile profile = found.get();
            assertEquals(token, profile.getPasswordResetToken());
        }
        else{
            System.out.println("Token does not exist or is expired ");
        }
    }
}