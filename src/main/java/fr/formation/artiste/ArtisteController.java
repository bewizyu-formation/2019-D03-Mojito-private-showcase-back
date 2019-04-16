package fr.formation.artiste;

import fr.formation.hello.HelloController;
import fr.formation.user.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private  ArtisteRepository   artisteRepository;
    @GetMapping("/")
    public ResponseEntity getAllArtiste() {
       return ResponseEntity.ok(artisteRepository.findAll());

    }

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
    public ResponseEntity<Artiste> signin(
            @RequestParam String username,
            @RequestParam String namedArtist,
            @RequestParam String image,
            @RequestParam double grade,
            @RequestParam String longDescription,
            @RequestParam String shortDescription,
            @RequestParam String webSite,
            @RequestParam String phoneNumber,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String nomVille,
            @RequestParam String codeVille,
            @RequestParam String codeDept

    ) throws ArtisteException {

        logger.info("1. Controller Artiste signin: " +" || " + namedArtist + "  " +  phoneNumber) ;

          Artiste artiste =   artisteService.addNewArtiste(username,namedArtist, image, grade,
                    longDescription, shortDescription, webSite,
                    phoneNumber, passwordEncoder.encode(password),
                    email,
                    nomVille, codeVille ,codeDept );


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

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
        logger.info("1. Controller ListeArtiste object: " + artisteService.getArtisteByDept(codeDept) );

    }


}
