package com.lulamile.firstSpringBootApp.repository;

import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {
    Contact findContactByEmailsIgnoreCase(String emails);
}
