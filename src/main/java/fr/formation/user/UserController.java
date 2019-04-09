package fr.formation.user;

import fr.formation.hello.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/users")
public class UserController {

	/**
	 * The Logger.
	 */
	Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private UserService userService;


	@Autowired
	private PasswordEncoder passwordEncoder;






	/**
	 * Signup.
	 *
	 * @param username the username
	 * @param password the password
	 * @param roles    the roles
	 */
	@PutMapping("/")
	public void signup(@RequestParam String username, @RequestParam String password, @RequestParam String email,
										 @RequestParam String... roles) {

		userService.addNewUser(username, password, email, roles);
		logger.info("requ pass: " + password );


	}




	@PostMapping("/create")
	public void signin(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String nomVille,
						   @RequestParam String codeVille, @RequestParam String nomDept, @RequestParam String codeDept){

		logger.info("requ controller signin: " + username  + "password " +  password);

		userService.createNewUser(username, passwordEncoder.encode(password), email,nomVille,codeVille,nomDept,codeDept);




	}

}
