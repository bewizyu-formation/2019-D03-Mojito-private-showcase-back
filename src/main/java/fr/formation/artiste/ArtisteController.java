package fr.formation.artiste;

import fr.formation.hello.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.tree.VoidDescriptor;

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



        if (checkPassword(password)) {
            artisteService.addNewArtiste(namedArtist, image, grade,
                    longDescription, shortDescription, webSite,
                    phoneNumber, passwordEncoder.encode(password),
                    email, departmentChosen,
                    nomVille, codeVille);

            logger.info("1. Controller Artiste signin: " + departmentChosen.get(1) + " || " + namedArtist);
        }

    }

    @GetMapping("/details")
    public void getArtiste(@RequestParam long id) {
        artisteService.getArtiste(id);

        logger.info("1. Controller find Artiste details: " + id );
    }

    @GetMapping("/name")
    public void getArtisteByName(@RequestParam String name) {
        artisteService.getArtisteByName(name);

        logger.info("1. Controller find Artiste by Name : "+ name );
    }

    @GetMapping("/listByDept")
    public void getArtisteByDept(@RequestParam String codeDept) {

        artisteService.getArtisteByDept(codeDept);
        logger.info("1. Controller ListeArtiste find by dept: " + codeDept );


    }


    public boolean checkPassword(String password) {

        int min = 8;
        int digit = 0;
        int upCount = 0;
        int loCount = 0;
        if (password.length() >= min) {
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if (Character.isUpperCase(c)) {
                    upCount++;
                }
                if (Character.isLowerCase(c)) {
                    loCount++;
                }
                if (Character.isDigit(c)) {
                    digit++;
                }

            }
            if (loCount >= 1 && upCount >= 1 && digit >= 1) {
                logger.info("Passwod Correct " );
                return true;
            }
            else {
                logger.info("Passwod incorrect => LowerCase : " + loCount + ", UpperCase : "+ upCount +", Digit : " +digit );

            }
        }
        else {
            logger.info("Passwod incorrect => Lenght : "  + password.length() );

        }


        return false;
    }


}
