package com.lulamile.firstSpringBootApp.controller;

import ch.qos.logback.core.model.Model;
import com.lulamile.firstSpringBootApp.entity.Address;
import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.service.AddressService;
import com.lulamile.firstSpringBootApp.service.ContactService;
import com.lulamile.firstSpringBootApp.service.ProfileService;
import com.lulamile.firstSpringBootApp.utils.DTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
@Slf4j
@Controller
public class ViewController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ContactService contactService;
    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }
    @GetMapping("/register")
    public ModelAndView register(){
        Profile profile = new Profile();
        Contact contact = new Contact();
        Address address = new Address();
        DTO dto = new DTO(profile,contact,address);
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("dto",dto);
        return mav;
    }
    @PostMapping("/register")
    public String register_user(@ModelAttribute("dto") DTO dto){
        Profile profile = dto.getProfile();
        Contact contact = dto.getContact();
        Address address = dto.getAddress();

        contactService.saveContact(contact);
        addressService.saveAddress(address);
        profile.setContact(contact);
        profile.setAddress(address);
        profileService.saveProfile(profile);
        log.info(String.valueOf(profile));
        return "redirect:/login";
    }
    @GetMapping("/home")
    public ModelAndView home(){
        List<Profile> profiles = profileService.fetchProfiles();
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("profiles",profiles);
        return mav;
    }
    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
