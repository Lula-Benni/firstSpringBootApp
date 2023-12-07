package com.lulamile.firstSpringBootApp;

import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.entity.User;
import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class FirstSpringBootAppApplication {

	public static void main(String[] args) {

		var context=SpringApplication.run(FirstSpringBootAppApplication.class, args);
		//var user= context.getBean(User.class);
		//var profile= context.getBean(Profile.class);
		//log.info(user.toString());
		//log.info(profile.toString());
	}

}
