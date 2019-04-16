package fr.formation.user;

import fr.formation.geo.model.Commune;
import fr.formation.geo.model.Departement;
import fr.formation.geo.services.CommuneService;
import fr.formation.geo.services.DepartementService;
import fr.formation.hello.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private CommuneService communeService;

    private DepartementService departementService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(CommuneService communeService, DepartementService departementService) {
        this.communeService = communeService;
        this.departementService = departementService;
    }

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
        logger.info("requ pass: " + password);


    }

    /**
     * signin a new user
     *
     * @param username
     * @param password
     * @param email
     * @param nomVille
     * @param codeVille
     * @param codeDept
     */
    @PostMapping("/create")
    public void signin(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String nomVille,
                        @RequestParam String codeVille, @RequestParam String codeDept) {

        logger.info("( Start User controller , method = signin ) , code Departement " + codeDept + " , code Ville " + codeVille);
        userService.createNewUser(username, passwordEncoder.encode(password), email, nomVille, codeVille,  codeDept);


    }


}
