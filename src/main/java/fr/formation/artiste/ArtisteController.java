package fr.formation.artiste;

import fr.formation.hello.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Artiste controller.
 */
@RestController
@RequestMapping("/artistes")
public class ArtisteController {

    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(HelloController.class);


    @Autowired
    private ArtisteService artisteService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * Signin a new artist
     *
     * @param namedArtist
     * @param image
     * @param grade
     * @param longDescription
     * @param shortDescription
     * @param webSite
     * @param phoneNumber
     * @param password
     * @param email
     */
    @PostMapping("/create")
    public void signin(
            @RequestParam String namedArtist,
            @RequestParam String image,
            @RequestParam double grade,
            @RequestParam String longDescription,
            @RequestParam String shortDescription,
            @RequestParam String webSite,
            @RequestParam String phoneNumber,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam(value = "departmentChosen") List<String> departmentChosen,
            @RequestParam String nomVille,
            @RequestParam String codeVille) {

        logger.info("1. Controller Artiste signin: " + departmentChosen.get(1) + " || " + namedArtist);


        artisteService.addNewArtiste(namedArtist, image, grade,
                longDescription, shortDescription, webSite,
                phoneNumber, passwordEncoder.encode(password),
                email, departmentChosen,
                nomVille, codeVille);

    }


}
