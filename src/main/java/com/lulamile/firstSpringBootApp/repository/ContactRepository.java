package com.lulamile.firstSpringBootApp.repository;

import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
}
