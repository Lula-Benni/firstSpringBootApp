package com.lulamile.firstSpringBootApp.utils;

import com.lulamile.firstSpringBootApp.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import java.sql.Date;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DTO {
    private Profile profile;
    private Contact contact;
    private Address address;
}
