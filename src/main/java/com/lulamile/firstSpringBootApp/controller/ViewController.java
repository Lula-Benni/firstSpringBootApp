package com.lulamile.firstSpringBootApp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import com.lulamile.firstSpringBootApp.entity.Address;
import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.entity.Item;
import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.service.AddressService;
import com.lulamile.firstSpringBootApp.service.ContactService;
import com.lulamile.firstSpringBootApp.service.ItemService;
import com.lulamile.firstSpringBootApp.service.ProfileService;
import com.lulamile.firstSpringBootApp.utils.DTO;
import com.lulamile.firstSpringBootApp.utils.ItemDTO;
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
    @Autowired
    private ItemService itemService;
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
    @GetMapping("/addItem")
    public ModelAndView addItem(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Profile profile = profileService.fetchProfileByUserName(username);
        Item item = new Item();
        ItemDTO itemDto = new ItemDTO(item,profile);
        ModelAndView mav = new ModelAndView("addItem");
        mav.addObject("itemDto",itemDto);
        return mav;
    }
    @PostMapping("/addItem")
    public String item_user(@ModelAttribute("itemDto") ItemDTO itemDto){
        Item item = itemDto.getItem();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Profile profile = profileService.fetchProfileByUserName(username);
        item.setProfile(profile);
        itemService.saveItem(item);
        return "redirect:/home";
    }
    @GetMapping("/home")
    public ModelAndView home(){
        List<Item> items = itemService.fetchItems();
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("items",items);
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
    @GetMapping("/viewProfile")
    public ModelAndView viewProfile() {

        Profile profile;
        Contact contact;
        Address address;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        profile = profileService.fetchProfileByUserName(username);
        contact = contactService.fetchContactById(profile.getContact().getContactId());
        address = profile.getAddress();

        DTO dto = new DTO(profile, contact, address);
        ModelAndView mav = new ModelAndView("viewProfile");
        mav.addObject("dto", dto);
        return mav;
    }
    @PostMapping("/viewProfile-{id}")
    @PreAuthorize("isAuthenticated()")
    public String updateProfile(@PathVariable("id") int id, @ModelAttribute("dto") DTO dto){
        Profile profile = dto.getProfile();
        Contact contact = dto.getContact();
        Address address = dto.getAddress();

        contactService.updateContact(id,contact);
        addressService.updateAddress(id,address);
        profileService.updateProfile(id,profile);
        return "redirect:/viewProfile";
    }
    @GetMapping("/itemProfile-{id}")
    public ModelAndView getItemProfile(@PathVariable("id") int id){
        Profile profile = profileService.fetchProfile(id);
        ModelAndView mav = new ModelAndView("itemProfile");
        mav.addObject("profile",profile);
        return mav;
    }
    @GetMapping("/viewMyItems")
    public ModelAndView getProfileItems(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Profile profile = profileService.fetchProfileByUserName(username);
        List<Item> items = profile.getItems();
        ModelAndView mav = new ModelAndView("viewMyItems");
        mav.addObject("items",items);
        return mav;
    }
}
