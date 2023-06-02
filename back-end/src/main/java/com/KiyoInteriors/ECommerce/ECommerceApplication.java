package com.KiyoInteriors.ECommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**

The "UserService" class provides methods for updating user details and converting a User object to a UserResponse object.
updateUser(UserRequest userDTO, String id): Updates the user details based on the provided UserRequest object and user ID.
*/
@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
