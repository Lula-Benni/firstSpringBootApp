package com.lulamile.firstSpringBootApp.repository;

import com.lulamile.firstSpringBootApp.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    public Profile findByFullNameIgnoreCase(String fullName);
    public List<Profile> findByFullNameContaining(String fullName);
    @Modifying
    @Query(value = "update profile set password = ?1 where email = ?2",
            nativeQuery=true)
    public void updatePassWordByEmail(String password,String email);
    Optional<Profile> findOneByUserNameIgnoreCase(String userName);

    Profile findByUserNameIgnoreCase(String userName);
    @Query("SELECT p FROM Profile as p inner join p.contact c join p.address WHERE c.emails=:emails")
    Optional<Profile> findByEmailsIgnoreCase(@Param("emails") String emails);
    Optional<Profile> findByPasswordResetToken(String token);
}
