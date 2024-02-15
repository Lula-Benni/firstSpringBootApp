package com.lulamile.firstSpringBootApp.controller;

import com.lulamile.firstSpringBootApp.service.*;
import com.lulamile.firstSpringBootApp.utils.email.EmailDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import com.lulamile.firstSpringBootApp.entity.Address;
import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.entity.Item;
import com.lulamile.firstSpringBootApp.entity.Profile;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    @Autowired
    private EmailService emailService;
    @Value("${site.domain}")
    private String site_domain;
    @Autowired
    private PasswordEncoder passwordEncoder;
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
        return "redirect:/register";
    }
    @GetMapping("/add-item")
    public ModelAndView addItem(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Profile profile = profileService.fetchProfileByUserName(username);
        Item item = new Item();
        ItemDTO itemDto = new ItemDTO(item,profile);
        ModelAndView mav = new ModelAndView("add-item");
        mav.addObject("itemDto",itemDto);
        return mav;
    }
    @PostMapping("/add-item")
    public String item_user(@ModelAttribute("itemDto") ItemDTO itemDto){
        Item item = itemDto.getItem();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Profile profile = profileService.fetchProfileByUserName(username);
        item.setProfile(profile);
        itemService.saveItem(item);
        return "redirect:/view-my-items";
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
    @GetMapping("/view-profile")
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
        ModelAndView mav = new ModelAndView("view-profile");
        mav.addObject("dto", dto);
        return mav;
    }
    @PostMapping("/view-profile/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String updateProfile(@PathVariable("id") int id, @ModelAttribute("dto") DTO dto){
        Profile profile = dto.getProfile();
        Contact contact = dto.getContact();
        Address address = dto.getAddress();
        contactService.updateContact(id,contact);
        addressService.updateAddress(id,address);
        profileService.updateProfile(id,profile);
        return "redirect:/view-profile";
    }
    @GetMapping("/view-my-items")
    public ModelAndView getProfileItems(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Profile profile = profileService.fetchProfileByUserName(username);
        List<Item> items = profile.getItems();
        ModelAndView mav = new ModelAndView("view-my-items");
        mav.addObject("items",items);
        return mav;
    }
    @GetMapping("/view-my-items/{id}/delete")
    @PreAuthorize("isAuthenticated()")
    public String deleteItem(@PathVariable("id") int id){
        Item item = itemService.fetchItemById(id);
        if(item!=null){
            itemService.deleteItem(item.getItemId());
        }
        return "redirect:/view-my-items";
    }
    @GetMapping("/item-profile-{id}")
    public ModelAndView getItemProfile(@PathVariable("id") int id){
        Profile profile = profileService.fetchProfile(id);
        ModelAndView mav = new ModelAndView("item-profile");
        mav.addObject("profile",profile);
        return mav;
    }
    @GetMapping("/edit-item-{id}")
    public ModelAndView editItem(@PathVariable("id") int id){
        Profile profile;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        profile = profileService.fetchProfileByUserName(username);
        Item item = itemService.fetchItemById(id);
        ItemDTO itemDTO = new ItemDTO(item,profile);
        ModelAndView mav = new ModelAndView("edit-item");
        mav.addObject("itemDTO",itemDTO);
        return mav;
    }
    @PostMapping("/edit-item/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String updateItem(@PathVariable("id") int id,@ModelAttribute("itemDTO") ItemDTO itemDTO){
        Item item = itemDTO.getItem();
        itemService.updateItem(id,item);
        return "redirect:/edit-item-"+id;
    }
    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        return "forgot-password";
    }
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("emails") String _email, RedirectAttributes attributes, Model model){
        Optional<Profile> profileOpt = profileService.fetchProfileByEmail(_email);
        if (profileOpt.isPresent()){
            Profile profile = profileService.fetchProfile(profileOpt.get().getProfileId());
            String reset_token = UUID.randomUUID().toString();
            profile.setPasswordResetToken(reset_token);
            profile.setPassword_reset_token_expDate(LocalDateTime.now().plusMinutes(15));
            profileService.updateProfile(profile.getProfileId(),profile);
            String resetMessage = "Here is your SellUrItem password reset  link: "+site_domain+"change-password?token="+reset_token;
            EmailDetails emailDetails = new EmailDetails(profile.getContact().getEmails(),resetMessage, "Reset Your SellUrItem Password");
            emailService.sendSimpleEmail(emailDetails);
        }
        else{
            log.info("**************Could not send Email**********");
        }
        return "redirect:/forgot-password";
    }
    @GetMapping("/change-password")
    public String changePassword(@RequestParam("token") String token,RedirectAttributes redirectAttributes,Model model){
        if(token.equals("")){
            log.info("Error invalid token empty ");
            return "redirect://forgot-password";
        }
        Optional<Profile> optionalProfile = profileService.fetchProfileByToken(token);
        if(optionalProfile.isPresent()){
            Profile profile = profileService.fetchProfile(optionalProfile.get().getProfileId());
            LocalDateTime now =LocalDateTime.now();
            if(now.isAfter(optionalProfile.get().getPassword_reset_token_expDate())){
                log.info("*******************Error: expired token**********************");
                return "redirect:/forgot-password";
            }
            model.addAttribute("profile",profile);
            return "change-password";
        }
        log.info("*******************Error: Invalid token**********************");
        return "redirect:/forgot-password";
    }
    @PostMapping("/change-password")
    public String changePasswordPost(@ModelAttribute("profile") Profile profile, RedirectAttributes attributes){
        Profile profileById = profileService.fetchProfile(profile.getProfileId());
        profileById.setPassword(passwordEncoder.encode(profile.getPassword()));
        profileById.setPasswordResetToken("");
        profileService.updateProfile(profile.getProfileId(),profileById);
        log.info("****************************password from: "+profileById.getUserName()+" updated***********************************");
        return "redirect:/login";
    }
}