package com.example.taskmanager;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import com.example.taskmanager.entity.Role;
// import com.example.taskmanager.entity.User;
// import com.example.taskmanager.repository.UserRepository;

// @SpringBootApplication
// public class TaskmanagerApplication implements CommandLineRunner {

// 	@Autowired
// 	private UserRepository userRepository;

// 	public static void main(String[] args) {
// 		SpringApplication.run(TaskmanagerApplication.class, args);
// 	}

// 	public void run(String... args)  {
// 		User adminAccount = userRepository.findByRole(Role.ADMIN);
// 		if (adminAccount == null) {
// 			User user = new User();
// 			user.setEmail("admin@gmail.com");
// 			user.setFirstname("admin");
// 			user.setLastname("admin");
// 			user.setRole(Role.ADMIN);
// 			user.setPassword(new BCryptPasswordEncoder().encode("admin123"));
// 			user.setEnabled(true);

// 			userRepository.save(user);
// 		}
// 	}

// }

@SpringBootApplication

public class TaskmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}

}


