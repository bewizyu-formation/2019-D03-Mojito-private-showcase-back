package fr.formation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Signup.
	 *
	 * @param username the username
	 * @param password the password
	 * @param roles    the roles
	 */
	@PutMapping("/")
	public void signup(@RequestParam String username, @RequestParam String password,
										 @RequestParam String... roles) {

		userService.addNewUser(username, password, roles);
		System.out.println("requ pass: " + password );


	}

	@PostMapping("/create")
	public void signin(@RequestParam String username, @RequestParam String password
	, @RequestParam String email, @RequestParam String nomVille

	) {

		userService.createNewUser(username, password, email,nomVille);
		System.out.println("requ controller signin: " + username );


	}

}
